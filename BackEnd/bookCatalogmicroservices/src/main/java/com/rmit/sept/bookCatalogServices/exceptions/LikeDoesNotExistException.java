package com.rmit.sept.bookCatalogServices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LikeDoesNotExistException extends RuntimeException {

    public LikeDoesNotExistException() {
        super("No like to remove from review");
    }
}
