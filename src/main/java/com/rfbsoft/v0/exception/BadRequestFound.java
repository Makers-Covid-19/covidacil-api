package com.rfbsoft.v0.exception;

import org.springframework.http.HttpStatus;

public class BadRequestFound extends ErrorFound {
    public BadRequestFound(Object details) {
        super(HttpStatus.BAD_REQUEST, details);
    }
}
