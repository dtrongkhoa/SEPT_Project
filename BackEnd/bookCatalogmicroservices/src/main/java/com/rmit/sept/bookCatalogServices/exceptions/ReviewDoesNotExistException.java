package com.rmit.sept.bookCatalogServices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReviewDoesNotExistException extends RuntimeException {

    public ReviewDoesNotExistException() {
        super("The review does not exist");
    }
}