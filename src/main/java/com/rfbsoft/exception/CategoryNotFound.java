package com.rfbsoft.exception;

public class CategoryNotFound extends EntityNotFound {
    public CategoryNotFound(Long id) {
        super(id + " Category");
    }
}
