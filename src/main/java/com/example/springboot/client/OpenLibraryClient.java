package com.example.springboot.client;

import com.example.springboot.config.FeignErrorConfig;
import com.example.springboot.config.FeignRetryConfig;
import com.example.springboot.dto.OpenLibrarySearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "openLibraryClient",
        url = "https://openlibrary.org",
        configuration = {FeignErrorConfig.class, FeignRetryConfig.class}
)
public interface OpenLibraryClient {

    @GetMapping("/search.json")
    OpenLibrarySearchResponse searchByAuthor(
            @RequestParam("author") String author
    );
}