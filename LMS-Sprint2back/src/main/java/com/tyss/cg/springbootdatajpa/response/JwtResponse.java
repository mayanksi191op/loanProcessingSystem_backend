package com.tyss.cg.springbootdatajpa.response;

import java.io.Serializable;


public class JwtResponse implements Serializable{
	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String email;
	private final Boolean error;
	private final String role;
	private final int userid;
//	private final String bearer = "Bearer ";
	
	public JwtResponse(String jwttoken, String email, String role, int userid, Boolean error) {
		this.jwttoken = jwttoken;
		this.email = email;
		this.error = error;
		this.role = role;
		this.userid = userid;
}	
			
	public String getToken() {
		return this.jwttoken;
	}
	public String getEmail() {
		return this.email;
	}
	
	public Boolean getError() {
		return error;
	}
	
	public String getRole() {
		return role;
	}

	public int getUserid() {
		return userid;
	}
	
}
