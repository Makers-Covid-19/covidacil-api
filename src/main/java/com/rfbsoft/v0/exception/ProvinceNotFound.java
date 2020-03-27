package com.rfbsoft.v0.exception;

public class ProvinceNotFound extends EntityNotFound {
    public ProvinceNotFound(Long id) {
        super(id + " District");
    }
}
