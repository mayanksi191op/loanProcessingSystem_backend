package com.tyss.cg.springbootdatajpa.exception;

@SuppressWarnings("serial")
public class UserNotAllowedException extends RuntimeException{
	public UserNotAllowedException(String message) {
		super(message);
	}
}
