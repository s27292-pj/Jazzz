package com.jaz.bookorder.responses;


import lombok.Data;

@Data
public class OrderResponse {
    private Integer bookId;
    private Integer orderQuantity;
}
