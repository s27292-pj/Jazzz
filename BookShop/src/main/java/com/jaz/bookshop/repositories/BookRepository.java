package com.jaz.bookshop.repositories;

import com.jaz.bookshop.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE upper(b.genre) = upper(:genre)")
    List<Book> findByGenre(@Param("genre") String genre);
}

