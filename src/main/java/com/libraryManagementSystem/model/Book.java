package com.libraryManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="BOOK")
public class Book {
	
	 @JsonProperty("id")
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

    @JsonProperty("title")
    @NotNull
    @NotEmpty
    private String title;

    @JsonProperty("author")
    @NotNull
    @NotEmpty
    private String author;

    @JsonProperty("publicationYear")
    @NotNull
    private String publicationYear;

    @JsonProperty("ISBN")
    @NotNull
    private String ISBN;
    
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
	

}
