package com.rfbsoft.v0.response;

import com.rfbsoft.v0.utils.ResponseType;
import org.springframework.http.HttpStatus;

public class SuccesResponse extends Response {
    public SuccesResponse(Object details) {
        super(HttpStatus.OK, ResponseType.SUCCES.getTitle(), details);
    }
}
