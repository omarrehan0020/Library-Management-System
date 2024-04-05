package com.libraryManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryManagementSystem.controllers.BookController;
import com.libraryManagementSystem.controllers.BorrowingRecordController;
import com.libraryManagementSystem.controllers.PatronController;
import com.libraryManagementSystem.model.Book;
import com.libraryManagementSystem.services.BookService;
import com.libraryManagementSystem.services.BorrowingRecordService;
import com.libraryManagementSystem.services.PatronService;

@SpringBootTest
class BookControllerTests {
	
	 private MockMvc mockMvc;

	    @Mock
	    private BookService bookService;

	    @Mock
	    private PatronService patronService;

	    @Mock
	    private BorrowingRecordService borrowingRecordService;

	    @InjectMocks
	    private BookController bookController;

	    @InjectMocks
	    private PatronController patronController;

	    @InjectMocks
	    private BorrowingRecordController borrowingRecordController;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	    }
	    
	    @Test
	    public void testGetAllBooks() throws Exception {
	        Book book1 = new Book();
	        book1.setId(1L);
	        book1.setTitle("Book 1");

	        Book book2 = new Book();
	        book2.setId(2L);
	        book2.setTitle("Book 2");

	        List<Book> books = Arrays.asList(book1, book2);

	        when(bookService.getAllBooks()).thenReturn(books);

	        ResponseEntity<List<Book>> response = bookController.getAllBooks();

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	        assertEquals(2, response.getBody().size());
	        verify(bookService, times(1)).getAllBooks();
	    }
	    
	    
	    @Test
	    public void testGetBookById() throws Exception {
	        Long id = 1L;
	        Book book = new Book();
	        book.setId(id);
	        book.setTitle("Book Title");

	        when(bookService.getBookById(id)).thenReturn(book);

	        ResponseEntity<Book> response = bookController.getBookById(id);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	        assertEquals(id, response.getBody().getId());
	        verify(bookService, times(1)).getBookById(id);
	    }
	    
	    
	    @Test
	    public void testAddBook() throws Exception {
	        Book bookToAdd = new Book();
	        bookToAdd.setTitle("Test Book");
	        bookToAdd.setAuthor("Test Author");
	        bookToAdd.setISBN("1234567890");
	        bookToAdd.setPublicationYear("2022");

	        when(bookService.addBook(bookToAdd)).thenReturn(bookToAdd);
	        
	        ResponseEntity<Book> response = bookController.addBook(bookToAdd);
	        System.out.println(response);
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertNotNull(response.getBody());
	        verify(bookService, times(1)).addBook(bookToAdd);
	        
	       
	    }
	    
	    @Test
	    public void testDeleteBook() throws Exception {
	        Long bookId = 1L;

	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", bookId))
	                .andExpect(MockMvcResultMatchers.status().isNoContent())
	                .andDo(print());
	    }
	    
	    
	    @Test
	    public void testUpdateBook() throws Exception {
	        // Create a Book object with updated data
	        Long bookId = 1L;
	        Book updatedBook = new Book();
	        updatedBook.setId(bookId);
	        updatedBook.setTitle("Updated Title");
	        updatedBook.setAuthor("Updated Author");
	        updatedBook.setISBN("9876543210");
	        updatedBook.setPublicationYear("2023");

	        // Stub the behavior of the updateBook method in the bookService mock
	        when(bookService.updateBook(bookId, updatedBook)).thenReturn(updatedBook);
	        
	        ResponseEntity<Book> response = bookController.updateBook(bookId , updatedBook);
	        System.out.println(response);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());

	        verify(bookService, times(1)).updateBook(bookId, updatedBook);
	    }

	    
	    




}
