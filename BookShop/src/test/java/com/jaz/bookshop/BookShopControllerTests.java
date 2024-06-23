package com.jaz.bookshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaz.bookshop.controllers.BookShopController;
import com.jaz.bookshop.dto.Author;
import com.jaz.bookshop.requests.BookCreateRequest;
import com.jaz.bookshop.requests.BookResponse;
import com.jaz.bookshop.requests.BookUpdateRequest;
import com.jaz.bookshop.services.BookShopService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookShopController.class)
@AutoConfigureMockMvc
public class BookShopControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookShopService bookShopService;

    @Test
    public void testGetBookById() throws Exception {
        BookResponse mockResponse = new BookResponse();
        Mockito.when(bookShopService.getBookById(Mockito.anyInt()))
                .thenReturn(ResponseEntity.ok(mockResponse));

        ResultActions resultActions = mockMvc.perform(get("/bookshop/getBookById/{id}", 1));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<BookResponse> mockResponseList = Arrays.asList(new BookResponse(), new BookResponse());
        Mockito.when(bookShopService.getAllBooks())
                .thenReturn(ResponseEntity.ok(mockResponseList));

        ResultActions resultActions = mockMvc.perform(get("/bookshop/getAllBooks"));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetBooksByGenre() throws Exception {
        List<BookResponse> mockResponseList = Collections.singletonList(new BookResponse());
        Mockito.when(bookShopService.getBooksByGenre(Mockito.anyString()))
                .thenReturn(ResponseEntity.ok(mockResponseList));

        ResultActions resultActions = mockMvc.perform(get("/bookshop/getBooksByGenre/{genre}", "Fiction"));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testCreateBook() throws Exception {
        BookCreateRequest request = new BookCreateRequest(
                "Sample Title",
                "Fiction",
                29.99,
                300,
                0,
                100,
                new Author()
        );

        BookResponse mockResponse = new BookResponse();
        Mockito.when(bookShopService.createBook(Mockito.any(BookCreateRequest.class)))
                .thenReturn(ResponseEntity.ok(mockResponse));

        ResultActions resultActions = mockMvc.perform(post("/bookshop/createBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookUpdateRequest request = new BookUpdateRequest(
                1,
                "Updated Title",
                "Fiction",
                39.99,
                400,
                0,
                200,
                new Author()
        );

        BookResponse mockResponse = new BookResponse();
        Mockito.when(bookShopService.updateBook(Mockito.any(BookUpdateRequest.class)))
                .thenReturn(ResponseEntity.ok(mockResponse));

        ResultActions resultActions = mockMvc.perform(post("/bookshop/updateBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Mockito.doNothing().when(bookShopService).deleteBook(Mockito.anyInt());

        ResultActions resultActions = mockMvc.perform(delete("/bookshop/deleteBook/{id}", 1));

        resultActions.andExpect(status().isOk());
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
