package com.libraryManagementSystem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libraryManagementSystem.model.Book;

@Service
public interface BookService {
	
	 List<Book> getAllBooks();

    Book getBookById(Long id) throws Exception;

    Book addBook(Book book);

    Book updateBook(Long id, Book book) throws Exception;

    void deleteBook(Long id) throws Exception;
	
}
