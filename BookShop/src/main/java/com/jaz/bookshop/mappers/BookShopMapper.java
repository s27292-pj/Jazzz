package com.jaz.bookshop.mappers;

import com.jaz.bookshop.dto.Author;
import com.jaz.bookshop.requests.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import com.jaz.bookshop.dto.Book;

@Mapper(componentModel = "spring",builder =  @Builder(disableBuilder = true))
public interface BookShopMapper {
    Book bookCreateRequestToBook(BookCreateRequest bookCreateRequest);
    Book bookUpdateRequestToBook(BookUpdateRequest bookUpdateRequest);
    BookResponse bookToBookResponse(Book book);

    AuthorResponse authorToAuthorResponse(Author author);

    Author authorResponseToAuthor(AuthorResponse authorResponse);

    OrderRequest bookToOrderRequest(Book book);

}
