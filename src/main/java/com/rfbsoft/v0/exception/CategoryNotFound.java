package com.rfbsoft.v0.exception;

public class CategoryNotFound extends EntityNotFound {
    public CategoryNotFound(Long id) {
        super(id + " Category");
    }
}
