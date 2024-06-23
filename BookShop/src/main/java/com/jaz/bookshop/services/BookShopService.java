package com.jaz.bookshop.services;

import com.jaz.bookshop.dto.Author;
import com.jaz.bookshop.dto.Book;
import com.jaz.bookshop.exceptions.InvalidAuthorException;
import com.jaz.bookshop.exceptions.InvalidBookException;
import com.jaz.bookshop.feign.BookOrderClient;
import com.jaz.bookshop.mappers.BookShopMapper;
import com.jaz.bookshop.repositories.AuthorRepository;
import com.jaz.bookshop.repositories.BookRepository;
import com.jaz.bookshop.requests.BookCreateRequest;
import com.jaz.bookshop.requests.BookResponse;
import com.jaz.bookshop.requests.BookUpdateRequest;
import com.jaz.bookshop.requests.OrderRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookShopService {
    private final BookRepository bookRepository;
    private final BookShopMapper bookShopMapper;
    private final AuthorRepository authorRepository;
    private final BookOrderClient bookOrderClient;

    @Transactional
    public ResponseEntity<BookResponse> getBookById(Integer bookID) {
        Book book = bookRepository.findById(bookID).orElseThrow(() -> new RuntimeException("Book not Found"));
        book.setViews(book.getViews() + 1);
        return ResponseEntity.ok(bookShopMapper.bookToBookResponse(book));
    }

    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll().stream().map(bookShopMapper::bookToBookResponse).collect(Collectors.toList()));
    }

    @Transactional
    public ResponseEntity<BookResponse> createBook(BookCreateRequest bookCreateRequest) {
        Author authorFromRequest = bookCreateRequest.getAuthor();
        Author author = authorRepository.findById(authorFromRequest.getId())
                .orElse(authorRepository.save(authorFromRequest));
        Book book = bookShopMapper.bookCreateRequestToBook(bookCreateRequest);
        validateBook(book);
        book.setAuthor(author);
        validateAuthor(author);
        bookRepository.save(book);
        return ResponseEntity.ok(bookShopMapper.bookToBookResponse(book));
    }

    public ResponseEntity<Void> deleteBook(Integer bookID) {
        bookRepository.deleteById(bookID);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<BookResponse> updateBook(BookUpdateRequest bookUpdateRequest) {
        bookRepository.save(bookShopMapper.bookUpdateRequestToBook(bookUpdateRequest));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<BookResponse>> getBooksByGenre(String genre) {
        return ResponseEntity.ok(bookRepository.findByGenre(genre).stream().map(bookShopMapper::bookToBookResponse).collect(Collectors.toList()));
    }

    public ResponseEntity<OrderRequest> orderBook(OrderRequest orderRequest) {
        Book book = bookRepository.findById(orderRequest.getId()).orElseThrow(() -> new RuntimeException("Book not Found"));
        Integer stock = book.getStock();
        if (stock < orderRequest.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }
        book.setStock(stock - orderRequest.getQuantity());
        bookRepository.save(book);
        System.out.println(orderRequest);
        System.out.println(orderRequest.getQuantity());
        bookOrderClient.orderBookRequest(orderRequest);
        return ResponseEntity.ok(orderRequest);
    }

    public ResponseEntity<Void> orderReport() {
        bookOrderClient.orderReport();
        return ResponseEntity.ok().build();
    }

    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new InvalidBookException("Title is required");
        }
        if (book.getGenre() == null || book.getGenre().isEmpty()) {
            throw new InvalidBookException("Genre is required");
        }
        if (book.getAuthor() == null) {
            throw new InvalidBookException("Author is required");
        }
    }

    private void validateAuthor(Author author) {
        if (author.getName() == null || author.getName().isEmpty()) {
            throw new InvalidAuthorException("Author name is required");
        }
    }
}
