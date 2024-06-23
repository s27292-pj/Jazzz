package com.jaz.bookorder.mappers;

import com.jaz.bookorder.dto.BookOrder;
import com.jaz.bookorder.dto.Order;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",builder =  @Builder(disableBuilder = true))
public interface BookOrderMapper{
    Order bookOrderToOrder(BookOrder bookOrder);
    BookOrder orderToOrderResponse(BookOrder bookorder);
}
