package ple.controllers.usercontrollers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ple.controllers.usercontrollers.dto.UserDTO;
import ple.db.DbManager;
import ple.db.SelectQueryCondition;

public class UserDAO {
	String tableName = "users";
	
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
