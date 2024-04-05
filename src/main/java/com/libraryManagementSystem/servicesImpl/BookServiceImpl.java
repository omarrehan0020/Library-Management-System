package com.libraryManagementSystem.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.libraryManagementSystem.Exception.BookNotFoundException;
import com.libraryManagementSystem.model.Book;
import com.libraryManagementSystem.repository.BookRepository;
import com.libraryManagementSystem.services.BookService;

@Repository
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository ;
	
	@Transactional
	@Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
	
	@Transactional
    @Override
    @Cacheable("bookById")
    public Book getBookById(Long id) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent())
        	throw new BookNotFoundException("Book with ID " + id + " not found");
        
        return optionalBook.get();
    }

	
	@Transactional
    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

	
	@Transactional
    @Override
    @CacheEvict(value = "bookById", key = "#id")
    public Book updateBook(Long id, Book book) throws Exception {
        Optional<Book> optionalExistingBook = bookRepository.findById(id);
        if (optionalExistingBook.isPresent()) {
            Book existingBook = optionalExistingBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setISBN(book.getISBN());
            if(book.getPublicationYear() != null)
            	existingBook.setPublicationYear(book.getPublicationYear());
            return bookRepository.save(existingBook);
        } else {
        	throw new BookNotFoundException("Book with ID " + id + " not found");
        }
    }

	
	@Transactional
    @Override
    public void deleteBook(Long id) throws Exception {
    	Optional<Book> optionalExistingBook = bookRepository.findById(id);
        if (optionalExistingBook.isPresent()) {
        	 bookRepository.deleteById(id);
        } else {
        	throw new BookNotFoundException("Book with ID " + id + " not found");
        }
       
    }



}
