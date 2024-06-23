package com.jaz.bookshop.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String genre;
    private Double price;
    private Integer pages;
    private Integer views = 0;
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
