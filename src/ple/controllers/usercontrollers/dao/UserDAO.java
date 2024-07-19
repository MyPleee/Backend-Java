package ple.controllers.usercontrollers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ple.controllers.usercontrollers.dto.UserDTO;
import ple.db.DbManager;
import ple.db.InsertQueryCondition;
import ple.db.SelectQueryCondition;
import ple.exceptions.customexceptions.PleException;

public class UserDAO {
	String tableName = "TEST.USERS";
	
	public UserDTO selectUser(SelectQueryCondition selectQuery){
		
		selectQuery.setTableName(this.tableName);
		
		DbManager dbManager = new DbManager();
		Connection conn = dbManager.getConnection();
		UserDTO userDto = new UserDTO();
		
		try (PreparedStatement ps = conn.prepareStatement(selectQuery.getQuery())) {
			ResultSet rs = ps.executeQuery();
			
	       if (rs.next()) {
	            userDto.setId(rs.getString("id"));
	            userDto.setName(rs.getString("name"));
	            userDto.setPassword(rs.getString("password"));
	            userDto.setUuid(rs.getString("uuid"));
	            userDto.setEmail(rs.getString("email"));
	        } else {
	            System.out.println("No user found with name 'aaa'.");
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release();
		}
		
		return userDto;
	}

	public void insertUser(UserDTO userDto) throws PleException {
		InsertQueryCondition insertQuery = new InsertQueryCondition();
		
		insertQuery.setTableName(this.tableName);
		insertQuery.addInsertValue("uuid", userDto.getUuid());
		insertQuery.addInsertValue("id", userDto.getId());
		insertQuery.addInsertValue("password", userDto.getPassword());
		insertQuery.addInsertValue("name", userDto.getName());
		insertQuery.addInsertValue("email", userDto.getEmail());
		
		DbManager dbManager = new DbManager();
		Connection conn = dbManager.getConnection();
		
		try (PreparedStatement ps = conn.prepareStatement(insertQuery.getQuery())) {
			
	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("User inserted successfully.");
	        } else {
	            System.out.println("User insertion failed.");
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release();
		}
		
	}
	
	/*
	public static void main(String[] args) {
		
		DbPoolManager dbPool = DbPoolManager.getInstance();
		try {
			dbPool.initializePool();
		} catch (InitialException e) {
			e.printStackTrace();
		}
		UserDAO dao = new UserDAO();
		SelectQueryCondition qc = new SelectQueryCondition("TEST.USERS");
		qc.addWhereCondition("", "name", "aaa");
		
		System.out.println(qc.getQuery());
		
		UserDTO user = (UserDTO) dao.selectUser(qc);
		
		
		System.out.println(user.toString());
		
	}
	*/
}
