package com.example.springboot.services;

import com.example.springboot.events.BookCreatedEvent;
import com.example.springboot.events.BookDeletedEvent;
import com.example.springboot.events.BookUpdatedEvent;
import com.example.springboot.exceptions.ResourceNotFoundException;
import com.example.springboot.entities.Author;
import com.example.springboot.entities.Book;
import com.example.springboot.repositories.AuthorRepository;
import com.example.springboot.repositories.BookRepository;
import com.example.springboot.dtos.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final IsbnGenerateService isbnGenerateService;
    private final ApplicationEventPublisher eventPublisher;
    private static final Logger log =
            LoggerFactory.getLogger(BookService.class);


    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, IsbnGenerateService isbnGenerateService, ApplicationEventPublisher eventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.isbnGenerateService = isbnGenerateService;
        this.eventPublisher = eventPublisher;
    }

    // Convert Book entity to BookDTO
    private BookDTO mapToDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthorName());
    }

    // Convert BookDTO to Book entity
    private Book mapToEntity(BookDTO bookDTO) {
        Author author = authorRepository.findByName(bookDTO.getAuthor())
                .orElseGet(() -> {
                    Author a = new Author();
                    a.setName(bookDTO.getAuthor());
                    return authorRepository.save(a);
                });
        String isbn = isbnGenerateService.generateCode();
        return new Book(bookDTO.getTitle(), author, isbn);
    }

    // Get all books
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get a single book by ID
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return mapToDTO(book);
    }

    // Create a new book
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = mapToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        eventPublisher.publishEvent(
                new BookCreatedEvent(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor().getName())
        );
        log.info("Created book with Title={} and Author={}", bookDTO.getTitle(), bookDTO.getAuthor());
        return mapToDTO(savedBook);
    }

    // Update an existing book
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        existingBook.setTitle(bookDTO.getTitle());
        Author author = authorRepository.findByName(bookDTO.getAuthor())
                .orElseGet(() -> {
                    Author a = new Author();
                    a.setName(bookDTO.getAuthor());
                    return authorRepository.save(a);
                });
        existingBook.setAuthor(author);
        Book updatedBook = bookRepository.save(existingBook);
        eventPublisher.publishEvent(
                new BookUpdatedEvent(updatedBook.getId(), updatedBook.getTitle(), author.getName())
        );
        log.info("Updated book with Title={} and Author={}", bookDTO.getTitle(), bookDTO.getAuthor());
        return mapToDTO(updatedBook);
    }

    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            eventPublisher.publishEvent(new BookDeletedEvent(id));
            log.info("Deleted book");
        } else {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
    }
}
