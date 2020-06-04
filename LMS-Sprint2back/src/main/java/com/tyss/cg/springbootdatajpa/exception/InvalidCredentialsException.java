package com.tyss.cg.springbootdatajpa.exception;

@SuppressWarnings("serial")
public class InvalidCredentialsException extends RuntimeException{
	public InvalidCredentialsException(String message) {
		super(message);
	}
}
