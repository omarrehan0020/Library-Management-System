package com.libraryManagementSystem.services;



import com.libraryManagementSystem.model.BorrowingRecord;

public interface BorrowingRecordService {
	
    BorrowingRecord createBorrowingRecord(Long bookId, Long patronId) throws Exception;
     BorrowingRecord recordReturn(Long bookId, Long patronId);

}
