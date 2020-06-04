package com.tyss.cg.springbootdatajpa.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyss.cg.springbootdatajpa.entity.User;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private User user;
	
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			user = null;
			User user2;
			try {
				user2 = getUserInfo(request);
				return user2.getEmail();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		
		return super.obtainUsername(request);
	}
	
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
		
			User user2;
			try {
				user2 = getUserInfo(request);
				return user2.getPassword();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		
		return super.obtainPassword(request);
	}
	
	private User getUserInfo(HttpServletRequest request) throws IOException {
		if (user == null) {
			ObjectMapper mapper = new ObjectMapper();
			String json = "";
			BufferedReader reader = request.getReader();
			while (reader.ready()) {
				json = json + reader.readLine();
			}
			user = mapper.readValue(json, User.class);
		}
		return user;
	}
	
} //end of class
