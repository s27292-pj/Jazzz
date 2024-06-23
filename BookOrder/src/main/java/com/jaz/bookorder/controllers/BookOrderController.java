package com.jaz.bookorder.controllers;


import com.jaz.bookorder.dto.BookOrder;
import com.jaz.bookorder.dto.Order;
import com.jaz.bookorder.services.BookOrderService;
import com.lowagie.text.DocumentException;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-order")
public class BookOrderController {
    private final BookOrderService bookOrderService;

    @PostMapping("/orderBookRequest")
    public ResponseEntity<Order> orderBookRequest(BookOrder bookOrder) {
        return bookOrderService.orderBookRequest(bookOrder);
    }

    @GetMapping("/orderReport")
    public ResponseEntity<ByteArrayResource> getOrderReport() throws IOException, DocumentException {
        byte[] pdfBytes = bookOrderService.generateOrderReport();

        // Prepare HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=orders.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new ByteArrayResource(pdfBytes));
    }
}
