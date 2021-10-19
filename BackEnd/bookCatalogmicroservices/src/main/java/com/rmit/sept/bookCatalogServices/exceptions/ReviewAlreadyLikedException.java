package com.rmit.sept.bookCatalogServices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReviewAlreadyLikedException extends RuntimeException {

    public ReviewAlreadyLikedException() {
        super("The user has already like the post");
    }
}


