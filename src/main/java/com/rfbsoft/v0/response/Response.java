package com.rfbsoft.v0.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class Response implements ApiResponse {


    private HttpStatus httpStatus;

    private LocalDateTime timestamp;

    private String message;

    private Object data;

    public Response(HttpStatus httpStatus, String message, Object details) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = details;
        this.timestamp = LocalDateTime.now();
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
