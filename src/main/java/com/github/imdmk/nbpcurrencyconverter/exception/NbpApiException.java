package com.github.imdmk.nbpcurrencyconverter.exception;

public final class NbpApiException extends RuntimeException {

    public NbpApiException(String message) {
        super(message);
    }

    public NbpApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public NbpApiException(Throwable cause) {
        super(cause);
    }
}