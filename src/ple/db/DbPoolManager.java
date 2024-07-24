package ple.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import ple.config.DbPoolProperties;
import ple.exceptions.customexceptions.FinalizeException;
import ple.exceptions.customexceptions.InitialException;
import ple.exceptions.exceptiontypes.InitialErrorType;

public class DbPoolManager {
	
	private static DbPoolManager instance = new DbPoolManager();
	private LinkedList<Connection> connectionPool = new LinkedList<>();

	
	public static DbPoolManager getInstance() {
		return DbPoolManager.instance;
	}
	
	
	// ===================== 서버와 관련된 메서드 =====================
	/**
	 * pool에 db.properties에 설정된 만큼의 커넥션 생성
	 * @throws InitialException 
	 */
	public void initializePool() throws InitialException {
		try {
			synchronized(this.connectionPool) {
				for (int i = 0; i < DbPoolProperties.getInitPoolSize(); i++) {
					this.connectionPool.add(this.createNewConnectionForPool());
				}
			}
			System.out.println("Making dbpool... Success");
		} catch (ClassNotFoundException e) {
			throw new InitialException(e, InitialErrorType.ClassNotFoundError);
		} catch (SQLException e) {
			throw new InitialException(e, InitialErrorType.SqlError);
		}
	}
	
	/**
	 * 서버 종료시 connection pool에 있는 connection 해제
	 * @throws FinalizeException
	 */
	public void closePool() throws FinalizeException{
        synchronized (this.connectionPool) {   
            try {
            	while (!this.connectionPool.isEmpty()) {
                    Connection connection = connectionPool.removeFirst();
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
            	}
            	System.out.println("Closing dbpool... Success");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        }
    }
	

	// ===================== DbManager와 관련된 메서드 =====================
	
	/**
	 * 커넥션 1개 생성 후 반환
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection createNewConnectionForPool() throws ClassNotFoundException, SQLException {
		Class.forName(DbPoolProperties.getDbDriver());
		return DriverManager.getConnection(DbPoolProperties.getDbUrl(), DbPoolProperties.getDbUser(), DbPoolProperties.getDbPassword());
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
					this.connectionPool.wait();
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
				if (connectionPool.size() < DbPoolProperties.getMaxPoolSize()) {
					connectionPool.addLast(connection);
				} else {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				this.connectionPool.notifyAll();
			}
		}
	}

}
