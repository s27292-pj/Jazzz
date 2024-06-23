package com.jaz.bookshop.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequest {
    private Integer id;
    private String title;
    private Integer Quantity;
}
