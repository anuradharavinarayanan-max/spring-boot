package com.example.springboot.dto;

import org.springframework.http.ResponseEntity;
import jakarta.validation.constraints.*;

import java.util.Optional;

public class BookDTO {
    private Long id;
    @Size(min = 0, max = 100)
    @NotBlank(message = "Name is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    // Constructors, getters, and setters

    public BookDTO() {}

    public BookDTO(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}