package com.rfbsoft.exception;

import com.rfbsoft.response.Response;
import com.rfbsoft.utils.ResponseType;
import org.springframework.http.HttpStatus;

public class ErrorFound extends Response {
    public ErrorFound(HttpStatus status, Object details) {
        super(status, ResponseType.ERROR.getTitle(), details);

    }
}
