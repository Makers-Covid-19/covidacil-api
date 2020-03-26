package com.rfbsoft.exception;


public class NeighborhoodNotFound extends EntityNotFound {

    public NeighborhoodNotFound(Long id) {
        super(id + " - id Neighborhood");
    }
}
