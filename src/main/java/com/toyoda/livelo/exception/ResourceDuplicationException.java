package com.toyoda.livelo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceDuplicationException extends RuntimeException {

    public ResourceDuplicationException() {
       super("Resource Duplicate");
    }
    public ResourceDuplicationException(String message) {
        super(message);
    }
}
