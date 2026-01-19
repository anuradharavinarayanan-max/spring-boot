package com.example.springboot.services;

import com.example.springboot.clients.OpenLibraryClient;
import com.example.springboot.dtos.OpenLibrarySearchResponse;
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