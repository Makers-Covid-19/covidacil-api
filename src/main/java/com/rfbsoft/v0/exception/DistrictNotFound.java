package com.rfbsoft.v0.exception;

public class DistrictNotFound extends EntityNotFound {
    public DistrictNotFound(Long id) {
        super(id + " - id District");
    }
}
