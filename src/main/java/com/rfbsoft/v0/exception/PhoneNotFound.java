package com.rfbsoft.v0.exception;

public class PhoneNotFound extends EntityNotFound {
    public PhoneNotFound(Long id) {
        super(id + " Phone");
    }
}
