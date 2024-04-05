package com.libraryManagementSystem.Exception;

public class BorrowingRecordNotFoundException extends RuntimeException {


	private static final long serialVersionUID = -5944730986194766795L;
	
	public BorrowingRecordNotFoundException(String message) {
		super(message);
	}


}
