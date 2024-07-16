package ple.controllers.usercontrollers.dto;

import ple.dto.CommonDTO;

public class UserDTO implements CommonDTO{
	private String uuid;
	private String id;
	private String password;
	private String name;
	private String email;
	
	public void setuuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public CommonDTO getOriginalDto() {
		return this;
	}
}
