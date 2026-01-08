package com.example.springboot.dto;

import java.util.List;

public record OpenLibrarySearchResponse(
        List<BookDoc> docs
) {}

