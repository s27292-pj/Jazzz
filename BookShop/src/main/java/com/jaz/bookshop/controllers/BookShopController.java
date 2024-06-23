package com.jaz.bookshop.controllers;

import com.jaz.bookshop.requests.BookCreateRequest;
import com.jaz.bookshop.requests.BookResponse;
import com.jaz.bookshop.requests.BookUpdateRequest;
import com.jaz.bookshop.requests.OrderRequest;
import com.jaz.bookshop.services.BookShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookshop")
public class BookShopController {

    private final BookShopService bookShopService;

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Integer id) {
        return bookShopService.getBookById(id);
    }
    //@Secured(roles)
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return bookShopService.getAllBooks();
    }

    @GetMapping("/getBooksByGenre/{genre}")
    public ResponseEntity<List<BookResponse>> getBooksByGenre(@PathVariable String genre) {
        return bookShopService.getBooksByGenre(genre);
    }

    @PostMapping("/createBook")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookCreateRequest bookCreateRequest) {
        return bookShopService.createBook(bookCreateRequest);
    }

    @PostMapping("/updateBook")
    public ResponseEntity<BookResponse> updateBook(@RequestBody BookUpdateRequest bookUpdateRequest) {
        return bookShopService.updateBook(bookUpdateRequest);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookShopService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/orderBook")
    public ResponseEntity<OrderRequest> orderBook(@RequestBody OrderRequest orderRequest) {
        return bookShopService.orderBook(orderRequest);
    }

    @GetMapping("/orderReport")
    public ResponseEntity<Void> orderReport() {
        return bookShopService.orderReport();
    }
}
