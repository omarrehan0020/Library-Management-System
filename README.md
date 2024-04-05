# Library-Management-System
Library Management System to allow librarians to manage books, patrons, and borrowing records.





Assumptions
Every Patron can borrow the same book for one time
Relation between book and borrowing record is one to many
Relation between patron and borrowing record is one to many

Notes
-	H2 used for database
-	Implemented Validation using spring annotations
-	Implemented Exception like 
o	PatronNotFoundException
o	BookNotFoundException
o	BorrowingRecordsAlreadyExists
o	BorrowingRecordNotFoundException
-	Implemented logging using Aspect Oriented Programming
-	Implemented Caching Using  caffeine
-	Implemented unit tests to validate the functionality of API endpoints using Junit test and Mockito
-	Implement declarative transaction management using Spring's @Transactional annotation to ensure data integrity during critical operations.






To Run the Project 
1-	Clone the repository
2-	open project in STS or different IDE
3-	Run the project as Springboot App

Use Postman to test APIs
Add http://localhost:8080 before each end point
API Endpoints:
● Implement RESTful endpoints to handle the following operations:
● Book management endpoints:
● GET /api/books: Retrieve a list of all books.
● GET /api/books/{id}: Retrieve details of a specific book by ID.
● POST /api/books: Add a new book to the library.
● PUT /api/books/{id}: Update an existing book's information.
● DELETE /api/books/{id}: Remove a book from the library.
● Patron management endpoints:
● GET /api/patrons: Retrieve a list of all patrons.
● GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
● POST /api/patrons: Add a new patron to the system.
● PUT /api/patrons/{id}: Update an existing patron's information.
● DELETE /api/patrons/{id}: Remove a patron from the system.
● Borrowing endpoints:
● POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to
borrow a book.

● PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron







