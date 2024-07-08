package heesom.test.dbpool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import heesom.test.dbpool.DbManager;

public class TestDaoWithDb {
	String tableName = "test.users";
	
	String insertSql = "INSERT INTO " + tableName + " (id, name, email) VALUES (?, ?, ?)";
	
	String selectSql = "SELECT * FROM " + tableName + " WHERE id = ?";
	
	public void insertData(int id, String name, String email){

		DbManager dm = new DbManager();
		
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
        } catch (Exception e) {
        	dm.rollback();
        	e.printStackTrace();
        }
        
    }
	
	public void selectData(int id){

		DbManager dm = new DbManager();
		
        try {
        	Connection conn = dm.getConnection();
        	PreparedStatement ps = null;
        	String sql = selectSql;
        	
        	ps = conn.prepareStatement(sql);
        	
        	ps.setInt(1, id);
        	ResultSet rs = ps.executeQuery();
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
    }
    
    public static void main(String args[]) {
    	TestDaoWithDb db = new TestDaoWithDb();
    	
    	try {
    		db.insertData(2,  "name", "xxx@gmail.com");
    	} catch (Exception e) {
    		System.out.println("exception while insert");
    		e.printStackTrace();
    	}
    }
	

}
