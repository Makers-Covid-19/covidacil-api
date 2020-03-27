package com.rfbsoft.v0.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFound extends ErrorFound {
    public EntityNotFound(Object details) {
        super(HttpStatus.NOT_FOUND, details + " Not Found");
    }
}
