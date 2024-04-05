package com.libraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryManagementSystem.model.Patron;


public interface PatronRepository extends JpaRepository<Patron , Long> {

}
