package com.libraryManagementSystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagementSystem.model.BorrowingRecord;
import com.libraryManagementSystem.services.BorrowingRecordService;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("api")
public class BorrowingRecordController {
	@Autowired
	 private  BorrowingRecordService borrowingRecordService;

	@PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable @NotNull  Long bookId, @PathVariable @NotNull  Long patronId) throws Exception {
        BorrowingRecord borrowingRecord = borrowingRecordService.createBorrowingRecord(bookId, patronId);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowingRecord);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable @NotNull  Long bookId, @PathVariable @NotNull  Long patronId) {
    	 BorrowingRecord borrowingRecord =  borrowingRecordService.recordReturn(bookId, patronId);
        return ResponseEntity.status(HttpStatus.OK).body(borrowingRecord);
    }
    

}
