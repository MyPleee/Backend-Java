package ple.controllers.usercontrollers.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import ple.exceptions.customexceptions.PleException;
import ple.util.UUIDFactory;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDTO {
	
	private String uuid;
	private String id;
	private String password;
	private String name;
	private String email;

	// ========= setter =========
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
	
	// ========= getter =========
	public String getUuid() throws PleException {
		this.uuid = UUIDFactory.getInstance().createNewUUID().toString();
		return this.uuid;
	}
	
	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
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
