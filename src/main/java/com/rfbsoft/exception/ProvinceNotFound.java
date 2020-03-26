package com.rfbsoft.exception;

public class ProvinceNotFound extends EntityNotFound {
    public ProvinceNotFound(Long id) {
        super(id + " District");
    }
}
