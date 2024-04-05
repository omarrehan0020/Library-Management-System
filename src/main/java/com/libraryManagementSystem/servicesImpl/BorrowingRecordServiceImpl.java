package com.libraryManagementSystem.servicesImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.libraryManagementSystem.Exception.BorrowingRecordIsAlreadyExistException;
import com.libraryManagementSystem.Exception.BorrowingRecordNotFoundException;
import com.libraryManagementSystem.model.Book;
import com.libraryManagementSystem.model.BorrowingRecord;
import com.libraryManagementSystem.model.Patron;
import com.libraryManagementSystem.repository.BorrowingRecordRepository;
import com.libraryManagementSystem.services.BookService;
import com.libraryManagementSystem.services.BorrowingRecordService;
import com.libraryManagementSystem.services.PatronService;

@Repository
public class BorrowingRecordServiceImpl implements BorrowingRecordService {
	
	@Autowired
	private BorrowingRecordRepository borrowingRecordRepository ;
	
	@Autowired
	private BookService bookService ;
	
	@Autowired
	private PatronService patronService ;
	
	
	@Transactional
	@Override
    public BorrowingRecord createBorrowingRecord(Long bookId, Long patronId) throws Exception {
		Optional<BorrowingRecord> optionalBorrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);
        if (optionalBorrowingRecord.isPresent()) {
           throw new BorrowingRecordIsAlreadyExistException("Book with ID " + bookId + "and Patron with ID " + patronId + " is Already existed");
        } 
    	  Book book = bookService.getBookById(bookId);
          Patron patron = patronService.getPatronById(patronId);

          BorrowingRecord borrowingRecord = new BorrowingRecord();
          borrowingRecord.setBook(book);
          borrowingRecord.setPatron(patron);
          borrowingRecord.setBorrowingDate(LocalDate.now());
        return borrowingRecordRepository.save(borrowingRecord);
    }
	
	@Transactional
	@Override
    public BorrowingRecord recordReturn(Long bookId, Long patronId) {
        Optional<BorrowingRecord> optionalBorrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);
        if (optionalBorrowingRecord.isPresent()) {
            BorrowingRecord borrowingRecord = optionalBorrowingRecord.get();
            
            borrowingRecord.setReturnDate(LocalDate.now());             
            return borrowingRecordRepository.save(borrowingRecord);
        } else {
            throw new BorrowingRecordNotFoundException("Book with ID " + bookId + "and Patron with ID " + patronId + " not found");
        }
    
    }
    

    
    
}
