package com.techgrains.exception;

public class TGForbiddenException extends RuntimeException {
    public TGForbiddenException() {
        super();
    }

    public TGForbiddenException(String message) {
        super(message);
    }

    public TGForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TGForbiddenException(Throwable cause) {
        super(cause);
    }

    protected TGForbiddenException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
