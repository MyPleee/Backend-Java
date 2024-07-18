package ple.controllers.usercontrollers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
	
	@JsonProperty("uuid")
	private String uuid;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("email")
	private String email;

	public void setUuid(String uuid) {
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("uuid").append("-").append(this.uuid).append("\n")
		.append("id").append("-").append(this.id).append("\n")
		.append("password").append("-").append(this.password).append("\n")
		.append("name").append("-").append(this.name).append("\n")
		.append("email").append("-").append(this.email).append("\n");
		
		return sb.toString();
	}
	
}
