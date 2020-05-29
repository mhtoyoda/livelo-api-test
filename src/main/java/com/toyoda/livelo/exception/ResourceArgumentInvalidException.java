package com.toyoda.livelo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceArgumentInvalidException extends RuntimeException {

    public ResourceArgumentInvalidException() {
       super("Resource Argument Invalid");
    }
    public ResourceArgumentInvalidException(String message) {
        super(message);
    }
}
