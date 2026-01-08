package com.example.springboot.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Book> books;

    public String getName() {
        return this.name;
    }

    public void setName(String author) {
        this.name = author;
    }

    public Long getId() {
        return this.id;
    }

    public List<Book> getBooks() {
        return books;
    }

    // getters & setters
}

