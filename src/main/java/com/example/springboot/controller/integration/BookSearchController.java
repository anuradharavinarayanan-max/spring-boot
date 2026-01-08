package com.example.springboot.controller.integration;

import com.example.springboot.dto.OpenLibrarySearchResponse;
import com.example.springboot.service.OpenLibraryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookSearchController {

    private final OpenLibraryService service;

    public BookSearchController(OpenLibraryService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public OpenLibrarySearchResponse searchBooks(
            @RequestParam String author
    ) {
        return service.searchByAuthor(author);
    }
}

