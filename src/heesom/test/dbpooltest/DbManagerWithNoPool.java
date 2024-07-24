package heesom.test.dbpooltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ple.config.DbPoolProperties;

public class DbManagerWithNoPool {
	
	Connection connection;
	
	public DbManagerWithNoPool() {
		
		try {
			Class.forName(DbPoolProperties.getDbDriver());
			this.connection = DriverManager.getConnection(DbPoolProperties.getDbUrl(), DbPoolProperties.getDbUser(), DbPoolProperties.getDbPassword());
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
