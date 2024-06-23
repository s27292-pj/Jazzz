package com.jaz.bookshop.repositories;

import com.jaz.bookshop.dto.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
}
