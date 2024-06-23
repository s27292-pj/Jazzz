package com.jaz.bookshop.feign;


import com.jaz.bookshop.requests.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient(name = "book-order", url = "http://192.168.0.81:8081")
public interface BookOrderClient {
    @PostMapping("/book-order/orderBookRequest")
    void orderBookRequest(OrderRequest orderRequest);

    @GetMapping("book-order/orderReport")
    void orderReport();
}
