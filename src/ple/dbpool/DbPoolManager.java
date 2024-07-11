package ple.dbpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import ple.exception.FinalizeException;
import ple.exception.InitialException;
import ple.prop.DbPoolProperties;
import ple.type.InitialErrorType;

public class DbPoolManager {
	
	private static DbPoolManager instance = new DbPoolManager();
	private LinkedList<Connection> connectionPool = new LinkedList<>();
	private DbPoolProperties dbPoolConfig;
	
	/**
	 * db.properties load
	 */
	private DbPoolManager() {
		dbPoolConfig = DbPoolProperties.getInstance();
	}
	
	public static DbPoolManager getInstance() {
		return DbPoolManager.instance;
	}
	
	/**
	 * pool에 db.properties에 설정된 만큼의 커넥션 생성
	 * @throws InitialException 
	 */
	public void initializePool() throws InitialException {
		try {
			synchronized(this.connectionPool) {
				for (int i = 0; i < dbPoolConfig.getInitPoolSize(); i++) {
					this.connectionPool.add(this.createNewConnectionForPool());
				}
			}
			System.out.println("complete making dbpool");
		} catch (ClassNotFoundException e) {
			throw new InitialException(e, InitialErrorType.ClassNotFoundError);
		} catch (SQLException e) {
			throw new InitialException(e, InitialErrorType.SqlError);
		}
	}
	
	public void closePool() throws FinalizeException{
        synchronized (this.connectionPool) {   
            try {
            	while (!this.connectionPool.isEmpty()) {
                    Connection connection = connectionPool.removeFirst();
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
            	}
            	System.out.println("complete closing dbpool");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        }
    }
	
	/**
	 * 커넥션 1개 생성 후 반환
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection createNewConnectionForPool() throws ClassNotFoundException, SQLException {
		Class.forName(this.dbPoolConfig.getDbDriver());
		return DriverManager.getConnection(this.dbPoolConfig.getDbUrl(), this.dbPoolConfig.getDbUser(), dbPoolConfig.getDbPassword());
	}
	
	/**
	 * dbpool에서 커넥션 1개 얻고 반환, 만약 풀이 비어있다면 스레드 대기
	 * @return Connection
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		synchronized(this.connectionPool) {
			while (this.connectionPool.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					throw new SQLException("Interrupted while waiting for a connection", e);
				}
			}
		}
		
		return connectionPool.removeFirst();
	}
	
	/**
	 * 커넥션 반환하면 dbpool에 추가 후 대기하고 있는 스레드 있다면 깨우기
	 * @param connection
	 */
	protected void releaseConnection(Connection connection) {
		synchronized(this.connectionPool) {
			if (connection != null) {
				// 현재 커넥션 풀 크기가 설정한 풀 크기 보다 작으면 커넥션 풀에 다시 추가
				if (connectionPool.size() < this.dbPoolConfig.getMaxPoolSize()) {
					connectionPool.addLast(connection);
				} else {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				notifyAll();
			}
		}
	}

}
