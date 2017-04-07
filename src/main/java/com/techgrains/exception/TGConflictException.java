package com.techgrains.exception;

public class TGConflictException extends RuntimeException {
    public TGConflictException() {
        super();
    }

    public TGConflictException(String message) {
        super(message);
    }

    public TGConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public TGConflictException(Throwable cause) {
        super(cause);
    }

    protected TGConflictException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
