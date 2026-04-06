package com.e_comm.security;

public class loginResponsdto {

	String jwt;
	
	String userid;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public loginResponsdto(String jwt, String userid) {
		super();
		this.jwt = jwt;
		this.userid = userid;
	}
}
