package com.margo.kataproject.dto.utils.exception;

public class TechnicalException extends Exception {
    public TechnicalException() {
        super();
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
