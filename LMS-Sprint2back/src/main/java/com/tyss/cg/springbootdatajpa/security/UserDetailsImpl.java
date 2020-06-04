package com.tyss.cg.springbootdatajpa.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tyss.cg.springbootdatajpa.entity.User;
import com.tyss.cg.springbootdatajpa.exception.InvalidCredentialsException;
import com.tyss.cg.springbootdatajpa.exception.UserNotAllowedException;

@SuppressWarnings("serial")
@Component
public class UserDetailsImpl implements UserDetails{
	
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		return Arrays.asList(authority);  	//converts to list(collection we need ot return) can pass multiple objects using this method
	}

	@Override
	public String getPassword() {
		try {
			return user.getPassword();
		} catch (Exception e) {
			throw new InvalidCredentialsException("Invalid Credentials!!! Please enter correct credentials.");
		}
		
	};

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
	//getters setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
