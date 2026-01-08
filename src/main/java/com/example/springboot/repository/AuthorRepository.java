package com.example.springboot.repository;

import com.example.springboot.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
    @Query("""
    select distinct a from Author a
    left join fetch a.books
""")
    List<Author> findAllWithBooks();
}