package com.jaz.bookshop.requests;


import com.jaz.bookshop.dto.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class BookCreateRequest {
    private String title;
    private String genre;
    private Double price;
    private Integer pages;
    private Integer views;
    private Integer stock;
    private Author author;
}
