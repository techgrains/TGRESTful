package com.techgrains.exception;

import org.springframework.http.HttpStatus;

class RestErrorResponse {
    private long timestamp;
    private HttpStatus status;
    private String error;
    private String message;

    public RestErrorResponse(HttpStatus status, String error, String message) {
        this.timestamp = System.currentTimeMillis();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}