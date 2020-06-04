package com.tyss.cg.springbootdatajpa.response;

import java.util.List;

public class Response<T> {
	private boolean error;
	
	private String message;
	
	T data;
	
	
	//List<T> product;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


//	public Response(boolean error, String message, List<T> product) {
//		this.error = error;
//		this.message = message;
//		this.product = product;
//	}
//
//	public List<T> getProduct() {
//		return product;
//	}
//
//	public void setProduct(List<T> product) {
//		this.product = product;
//	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Response(boolean error, String message, T data) {
		this.error = error;
		this.message = message;
		this.data = data;
	}
	
	public Response() {
	
	}
	
}
