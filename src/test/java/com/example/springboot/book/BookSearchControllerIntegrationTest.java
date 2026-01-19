package com.example.springboot.book;

import com.example.springboot.dtos.BookDoc;
import com.example.springboot.dtos.OpenLibrarySearchResponse;
import com.example.springboot.services.OpenLibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookSearchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenLibraryService service;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldSearchBooksByAuthor() throws Exception {

        BookDoc book = new BookDoc(
                "The Hobbit",
                List.of("J.R.R. Tolkien"), 1998

        );

        OpenLibrarySearchResponse response =
                new OpenLibrarySearchResponse(List.of(book));

        when(service.searchByAuthor("tolkien"))
                .thenReturn(response);

        mockMvc.perform(get("/api/books/search")
                        .param("author", "tolkien"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.docs[0].title").value("The Hobbit"))
                .andExpect(jsonPath("$.docs[0].author_name[0]").value("J.R.R. Tolkien"));
    }
}

