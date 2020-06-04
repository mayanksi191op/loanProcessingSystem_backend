package com.tyss.cg.springbootdatajpa.handlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyss.cg.springbootdatajpa.entity.User;
import com.tyss.cg.springbootdatajpa.response.Response;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
			Response<User> response2 = new Response<User>();
			response2.setMessage("Login Successful");
			
			response.setStatus(200);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(response2);
			PrintWriter out = response.getWriter();
			out.write(json);
		
	}

}
