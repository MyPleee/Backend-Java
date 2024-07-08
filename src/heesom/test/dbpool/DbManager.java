package heesom.test.dbpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import heesom.prop.DbPoolConfig;

public class DbManager {
	
	Connection connection;
	
	public DbManager() {
		DbPoolConfig dbPoolConfig = DbPoolConfig.getInstance();
		
		try {
			Class.forName(dbPoolConfig.getDbDriver());
			this.connection = DriverManager.getConnection(dbPoolConfig.getDbUrl(), dbPoolConfig.getDbUser(), dbPoolConfig.getDbPassword());
			this.connection.setAutoCommit(false);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
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
    
    public void release() {
    	try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
    }

    
}
