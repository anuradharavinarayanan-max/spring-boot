package com.example.springboot.dtos;

import java.util.List;

public record OpenLibrarySearchResponse(
        List<BookDoc> docs
) {}

