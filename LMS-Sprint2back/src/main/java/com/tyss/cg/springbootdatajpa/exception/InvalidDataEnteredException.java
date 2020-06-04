package com.tyss.cg.springbootdatajpa.exception;

@SuppressWarnings("serial")
public class InvalidDataEnteredException extends RuntimeException{
	public InvalidDataEnteredException(String message) {
		super(message);
	}
}
