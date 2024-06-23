package com.jaz.bookshop.requests;
import lombok.Data;

@Data
public class BookResponse {
    private Integer id;
    private String title;
    private String genre;
    private Double price;
    private Integer pages;
    private Integer views;
    private Integer stock;
    private AuthorResponse author;
}
