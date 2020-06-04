package com.tyss.cg.springbootdatajpa.exception;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tyss.cg.springbootdatajpa.response.Response;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity<?> handleCustomValidationError(MethodArgumentNotValidException ex ){
		Response<?> response=new Response(true,ex.getBindingResult().getFieldError().getDefaultMessage() , new ArrayList());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<Response<?>> handleException(UserNotFoundException ex){
		Response<?> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<Response<?>> handleException(NoDataPresentException ex){
		Response<?> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<Response<?>> handleException(InvalidDataEnteredException ex){
		Response<?> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<Response<?>> handleException(LoanNotFoundException ex){
		Response<?> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<Response<?>> handleException(EntryAlreadyExistsException ex){
		Response<?> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<Response<?>> handleException(ApplicationNotFoundException ex){
		Response<?> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<Response<?>> handleException(UserNotAllowedException ex){
		Response<?> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<Response<?>> handleException(InvalidCredentialsException ex){
		Response<?> response = new Response<>(true, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
