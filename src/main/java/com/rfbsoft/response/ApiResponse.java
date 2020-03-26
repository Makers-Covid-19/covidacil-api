package com.rfbsoft.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public interface ApiResponse {
    HttpStatus getHttpStatus();

    LocalDateTime getTimestamp();

    String getMessage();

    Object getData();
}
