package com.example.springboot.events;

public record BookUpdatedEvent(Long bookId, String title, String author) {}
