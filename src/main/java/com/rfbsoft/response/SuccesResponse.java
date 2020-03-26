package com.rfbsoft.response;

import com.rfbsoft.utils.ResponseType;
import org.springframework.http.HttpStatus;

public class SuccesResponse extends Response {
    public SuccesResponse(Object details) {
        super(HttpStatus.OK, ResponseType.SUCCES.getTitle(), details);
    }
}
