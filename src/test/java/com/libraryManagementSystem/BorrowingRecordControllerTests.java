package com.libraryManagementSystem;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.libraryManagementSystem.controllers.BorrowingRecordController;
import com.libraryManagementSystem.model.BorrowingRecord;
import com.libraryManagementSystem.services.BorrowingRecordService;


@SpringBootTest
class BorrowingRecordControllerTests {

    private MockMvc mockMvc;

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(borrowingRecordController).build();
    }

    @Test
    public void testBorrowBook() throws Exception {
        Long bookId = 1L;
        Long patronId = 1L;

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);

        when(borrowingRecordService.createBorrowingRecord(bookId, patronId)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(bookId, patronId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(borrowingRecordService, times(1)).createBorrowingRecord(bookId, patronId);
    }

    @Test
    public void testReturnBook() throws Exception {
        Long bookId = 1L;
        Long patronId = 1L;

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);

        when(borrowingRecordService.recordReturn(bookId, patronId)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.returnBook(bookId, patronId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(borrowingRecordService, times(1)).recordReturn(bookId, patronId);
    }
}
