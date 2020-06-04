package com.tyss.cg.springbootdatajpa.exception;

@SuppressWarnings("serial")
public class ApplicationNotFoundException extends RuntimeException{
	public ApplicationNotFoundException(String message){
		super(message);
	}
}
