package com.tyss.cg.springbootdatajpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tyss.cg.springbootdatajpa.services.UserServices;

@Component
public class UserDetailsSeviceImpl implements UserDetailsService {

	@Autowired
	private UserServices services;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl(); 
		userDetailsImpl.setUser(services.getByEmail(username));
		return userDetailsImpl;
	}
	
}
