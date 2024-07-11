package ple.dbpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import ple.prop.DbPoolProperties;

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
		if (instance == null) {
			DbPoolManager.instance = new DbPoolManager();
		}
		return DbPoolManager.instance;
	}
	
	/**
	 * pool에 db.properties에 설정된 만큼의 커넥션 생성
	 */
	protected void initializePool() {
		try {
			for (int i = 0; i < dbPoolConfig.getInitPoolSize(); i++) {
				connectionPool.add(this.createNewConnectionForPool());
			}
			System.out.println("dbpool 초기화 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("dbpool 생성 중 드라이버 가져올 수 없음 에러");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("dbpool 생성 중 sql 에러");
			e.printStackTrace();
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
	protected synchronized Connection getConnection() throws SQLException {
		
		while (connectionPool.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new SQLException("Interrupted while waiting for a connection", e);
			}
		}
		
		return connectionPool.removeFirst();
	}
	
	/**
	 * 커넥션 반환하면 dbpool에 추가 후 대기하고 있는 스레드 있다면 깨우기
	 * @param connection
	 */
	protected synchronized void releaseConnection(Connection connection) {
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
