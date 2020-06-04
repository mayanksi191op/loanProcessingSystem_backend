package com.tyss.cg.springbootdatajpa.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDetails {
	
	private Date timestamp;
	private String message;
	private String details;
}

