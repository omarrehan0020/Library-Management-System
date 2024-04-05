package com.libraryManagementSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagementSystem.model.Patron;
import com.libraryManagementSystem.services.PatronService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping("api/patrons")
public class PatronController {
	
	@Autowired
	private PatronService patronService;

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        List<Patron> patrons = patronService.getAllPatrons();
        return new ResponseEntity<>(patrons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable("id") @NotNull  Long id) {
        Patron patron = patronService.getPatronById(id);
        return new ResponseEntity<>(patron, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody @Valid Patron patron) {
        Patron createdPatron = patronService.addPatron(patron);
        return new ResponseEntity<>(createdPatron, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable("id") @NotNull  Long id, @RequestBody @Valid Patron patron) {
    	Patron updatedPatron = patronService.updatePatron(id ,patron);
        return new ResponseEntity<>(updatedPatron, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable("id") @NotNull  Long id) {
        patronService.deletePatron(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    

}