package com.example.springboot.service;

import com.example.springboot.client.OpenLibraryClient;
import com.example.springboot.dto.OpenLibrarySearchResponse;
import org.springframework.stereotype.Service;

@Service
public class OpenLibraryService {

    private final OpenLibraryClient client;

    public OpenLibraryService(OpenLibraryClient client) {
        this.client = client;
    }

    public OpenLibrarySearchResponse searchByAuthor(String author) {
        return client.searchByAuthor(author);
    }

}