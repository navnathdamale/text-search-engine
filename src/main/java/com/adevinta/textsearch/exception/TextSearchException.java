package com.adevinta.textsearch.exception;

public class TextSearchException extends RuntimeException {
    public TextSearchException(String message) {
        super(message);
    }

    public TextSearchException(String message, Throwable cause) {
        super(message, cause);
    }
}