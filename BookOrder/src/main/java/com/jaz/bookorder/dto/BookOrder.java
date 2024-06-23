package com.jaz.bookorder.dto;

import lombok.Data;

@Data
public class BookOrder {
    private Integer bookId;
    private String title;
    private Integer quantity;
}
