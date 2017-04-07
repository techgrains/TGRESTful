package com.techgrains.exception;

public class TGNotFoundException extends RuntimeException {
    public TGNotFoundException() {
        super();
    }

    public TGNotFoundException(String message) {
        super(message);
    }

    public TGNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TGNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TGNotFoundException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
