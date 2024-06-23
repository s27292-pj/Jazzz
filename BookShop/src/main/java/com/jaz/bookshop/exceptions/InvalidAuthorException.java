package com.jaz.bookshop.exceptions;

public class InvalidAuthorException extends RuntimeException{
    public InvalidAuthorException(String message) {
        super(message);
    }
}
