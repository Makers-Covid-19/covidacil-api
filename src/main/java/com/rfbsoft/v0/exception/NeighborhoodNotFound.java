package com.rfbsoft.v0.exception;


public class NeighborhoodNotFound extends EntityNotFound {

    public NeighborhoodNotFound(Long id) {
        super(id + " - id Neighborhood");
    }
}
