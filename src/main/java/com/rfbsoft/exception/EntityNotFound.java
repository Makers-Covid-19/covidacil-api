package com.rfbsoft.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFound extends ErrorFound {
    public EntityNotFound(Object details) {
        super(HttpStatus.NOT_FOUND, details + " Not Found");
    }
}
