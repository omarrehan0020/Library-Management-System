package com.libraryManagementSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryManagementSystem.model.Book; 

@Repository
public interface BookRepository extends JpaRepository<Book , Long> {
	
//	@Query("FROM Book WHERE email = :email")
//	Optional<Book> findUserByEmail(String email);
//	
//	@Query("FROM Book WHERE email = :email AND password = :password")
//	Optional<Book> findUserByEmailAndPassword(String email , String password);

}
