package com.example.springboot.dtos;

import java.util.List;

public record BookDoc(
        String title,
        List<String> author_name,
        Integer first_publish_year
) {}
