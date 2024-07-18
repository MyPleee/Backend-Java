package ple.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DbManager{
	private Connection connection;
	
	/**
	 * 객체 생성시 dbPool에서 커넥션 하나 빼서 저장
	 */
	public DbManager() {
		DbPoolManager dbPoolManager = DbPoolManager.getInstance();
		try {
			this.connection = dbPoolManager.getConnection();
			this.connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// =================== db 조작 관련 메서드 ==================
	/**
	 * dbpool에서 받아온 커넥션을 한 개 얻는 메서드
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		return this.connection;
	}
	
	public void commit(){
        try {
            this.connection.commit();
            System.out.println("Commit successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public void rollback() {
        try {
            this.connection.rollback();
            System.out.println("Rollback successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 커넥션을 다시 dbpool에 돌려주는 메서드
     */
    public void release() {
    	DbPoolManager dbPoolManager = DbPoolManager.getInstance();
    	dbPoolManager.releaseConnection(this.connection);
    	
    }
}
