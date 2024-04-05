package com.libraryManagementSystem.Exception;

public class PatronNotFoundException extends RuntimeException{
	
	
	private static final long serialVersionUID = 6246187459276111414L;

	public PatronNotFoundException(String message) {
		super(message);
	}

}

