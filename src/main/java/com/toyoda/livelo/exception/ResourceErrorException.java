package com.toyoda.livelo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceErrorException extends RuntimeException {

    public ResourceErrorException(String message) {
        super(message);
    }
}
