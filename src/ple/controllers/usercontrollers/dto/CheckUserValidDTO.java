package ple.controllers.usercontrollers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckUserValidDTO{
	
	@JsonProperty("isAdmin")
	private boolean isAdmin;
	
	@JsonProperty("isValidUser")
	private boolean isValidUser;

	@JsonProperty("id") 
	private String id;
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void setIsValidUser(boolean isValidUser) {
		this.isValidUser = isValidUser;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("isAdmin - ").append(this.isAdmin)
			.append("isValidUser - ").append(this.isValidUser)
			.append("id - ").append(this.id);
		
		return sb.toString();
	}

	
}
