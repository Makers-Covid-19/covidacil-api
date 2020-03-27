package com.rfbsoft.v0.exception;

import com.rfbsoft.v0.response.Response;
import com.rfbsoft.v0.utils.ResponseType;
import org.springframework.http.HttpStatus;

public class ErrorFound extends Response {
    public ErrorFound(HttpStatus status, Object details) {
        super(status, ResponseType.ERROR.getTitle(), details);

    }
}
