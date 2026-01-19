package com.example.springboot.events;

public record BookCreatedEvent(Long bookId, String title, String author) {}

