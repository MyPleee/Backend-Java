package heesom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import heesom.dbpool.DBManager;

public class TestDao {
	
	String insertSql = "INSERT INTO test.users (id, name, email) VALUES (?, ?, ?)";
	
	public void insertData(int id, String name, String email){

		DBManager dm = new DBManager();
		
        try {
        	Connection conn = dm.getConnection();
        	PreparedStatement ps = null;
        	String sql = insertSql;
        	
        	ps = conn.prepareStatement(sql);
        	
        	ps.setInt(1, id);
        	ps.setString(2, name);
        	ps.setString(3, email);
        	ps.executeUpdate();
        	
        	dm.commit();
        	System.out.println("Data inserted successfully!");
        } catch (Exception e) {
        	dm.rollback();
        	e.printStackTrace();
        }
        
    }
    
    public static void main(String args[]) {
    	TestDao db = new TestDao();
    	
    	try {
    		db.insertData(2,  "name", "xxx@gmail.com");
    	} catch (Exception e) {
    		System.out.println("exception while insert");
    		e.printStackTrace();
    	}
    }
	

}
