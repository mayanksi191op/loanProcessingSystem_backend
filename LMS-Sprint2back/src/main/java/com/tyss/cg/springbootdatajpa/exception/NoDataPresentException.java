package com.tyss.cg.springbootdatajpa.exception;

@SuppressWarnings("serial")
public class NoDataPresentException extends RuntimeException{
	
	public NoDataPresentException(String message) {
		super(message);
	}
}
