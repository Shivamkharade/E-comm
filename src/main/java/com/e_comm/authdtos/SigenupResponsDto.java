package com.e_comm.authdtos;

public class SigenupResponsDto {

	private String id;
	
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SigenupResponsDto(String id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	
	
}
