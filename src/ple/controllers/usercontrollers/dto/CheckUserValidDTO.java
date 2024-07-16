package ple.controllers.usercontrollers.dto;

import ple.dto.CommonDTO;

public class CheckUserValidDTO implements CommonDTO{
	
	private boolean isAdmin;
	private boolean isValidUser;
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
	public CommonDTO getOriginalDto() {
		return this;
	}
}
