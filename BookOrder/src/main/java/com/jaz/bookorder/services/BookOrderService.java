package com.jaz.bookorder.services;


import com.jaz.bookorder.dto.BookOrder;
import com.jaz.bookorder.dto.Order;
import com.jaz.bookorder.mappers.BookOrderMapper;
import com.jaz.bookorder.repositories.OrderRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.util.List;

@AllArgsConstructor
@Service
public class BookOrderService {
    private OrderRepository orderRepository;
    private BookOrderMapper bookOrderMapper;

    public ResponseEntity<Order> orderBookRequest(BookOrder bookOrder) {
        Order order = bookOrderMapper.bookOrderToOrder(bookOrder);
        orderRepository.save(order);
        return ResponseEntity.ok(order);
    }

    public byte[] generateOrderReport() throws DocumentException {
        List<Order> orders = orderRepository.findAll();

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();
        for (Order order : orders) {
            document.add(new Paragraph("Order ID: " + order.getId()));
            document.add(new Paragraph("Book ID: " + order.getBookId()));
            document.add(new Paragraph("Order Quantity: " + order.getOrderQuantity()));
            document.add(new Paragraph("-----------------------------------------------"));
        }
        document.close();

        return baos.toByteArray();
    }

}
