package com.example.springboot.service;

import com.example.springboot.dto.AuthorResponseDTO;
import com.example.springboot.dto.BookDTO;
import com.example.springboot.model.Author;
import com.example.springboot.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorResponseDTO> getAllAuthorsWithBooks() {
        return authorRepository.findAllWithBooks()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private AuthorResponseDTO mapToDTO(Author author) {
        AuthorResponseDTO dto = new AuthorResponseDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());

        List<BookDTO> books = author.getBooks()
                .stream()
                .map(book -> {
                    BookDTO b = new BookDTO();
                    b.setId(book.getId());
                    b.setTitle(book.getTitle());
                    b.setAuthor(book.getAuthorName());
                    return b;
                })
                .toList();

        dto.setBooks(books);
        return dto;
    }
}
