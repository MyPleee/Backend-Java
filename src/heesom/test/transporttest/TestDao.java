package heesom.test.transporttest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import heesom.test.dbpooltest.DbManagerWithNoPool;

public class TestDao {
	String tableName = "test.users";
	
	String insertSql = "INSERT INTO " + tableName + " (id, name, email) VALUES (?, ?, ?)";
	
	String selectSql = "SELECT * FROM" + tableName + "WHERE id = ?";
	
	public void insertData(int id, String name, String email){

		DbManagerWithNoPool dm = new DbManagerWithNoPool();
		
		Connection conn = null;
        PreparedStatement ps = null;
        
        try {
        	conn = dm.getConnection();
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
        } finally {
        	 if (ps != null) {
                 try {
                     ps.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
             if (conn != null) {
                 try {
                     conn.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
        }
        
    }
	
	public void selectData(int id){

		DbManagerWithNoPool dm = new DbManagerWithNoPool();
		
		Connection conn = null;
        PreparedStatement ps = null;
        
        try {
        	conn = dm.getConnection();
        	String sql = selectSql;
        	
        	ps = conn.prepareStatement(sql);
        	
        	ps.setInt(1, id);
        	ResultSet rs = ps.executeQuery();
        	
        	System.out.println("Data selected successfully!");
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	 if (ps != null) {
                 try {
                     ps.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
             if (conn != null) {
                 try {
                     conn.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
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
