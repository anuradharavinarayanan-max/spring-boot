package com.example.springboot.controller.author;

import com.example.springboot.dto.AuthorResponseDTO;
import com.example.springboot.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorResponseDTO> getAuthors() {
        return authorService.getAllAuthorsWithBooks();
    }
}
