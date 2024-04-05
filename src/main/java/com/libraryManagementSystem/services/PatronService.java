package com.libraryManagementSystem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libraryManagementSystem.model.Patron;



@Service
public interface PatronService {
	
	List<Patron> getAllPatrons();
    Patron getPatronById(Long id);
    Patron addPatron(Patron patron);
    Patron updatePatron(Long id ,Patron patron);
    void deletePatron(Long id);
	
}
