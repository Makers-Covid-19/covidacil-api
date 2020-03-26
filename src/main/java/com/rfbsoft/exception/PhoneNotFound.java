package com.rfbsoft.exception;

public class PhoneNotFound extends EntityNotFound {
    public PhoneNotFound(Long id) {
        super(id + " Phone");
    }
}
