package com.spring.learning.simpleapplication.exceptions;

public class BookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public BookNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
