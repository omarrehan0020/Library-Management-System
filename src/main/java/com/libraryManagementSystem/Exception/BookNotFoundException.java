package com.libraryManagementSystem.Exception;

public class BookNotFoundException  extends RuntimeException {
	


	private static final long serialVersionUID = 2361798154413778033L;

	public BookNotFoundException(String message) {
        super(message);
    }


}
