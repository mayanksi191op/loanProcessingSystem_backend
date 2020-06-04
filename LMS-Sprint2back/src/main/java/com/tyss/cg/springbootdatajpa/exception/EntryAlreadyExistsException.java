package com.tyss.cg.springbootdatajpa.exception;

@SuppressWarnings("serial")
public class EntryAlreadyExistsException extends RuntimeException{
	public EntryAlreadyExistsException(String message) {
		super(message);
	}

}
