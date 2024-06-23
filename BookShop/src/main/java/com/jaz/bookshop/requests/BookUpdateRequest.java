package com.jaz.bookshop.requests;

import com.jaz.bookshop.dto.Author;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class BookUpdateRequest {
    private Integer id;
    private String title;
    private String genre;
    private Double price;
    private Integer pages;
    private Integer views;
    private Integer stock;
    private Author author;
}
