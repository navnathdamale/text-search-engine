package com.adevinta.textsearch.exception;

public class TextSearchArgumentException extends TextSearchException {
    public TextSearchArgumentException(String message) {
        super(message);
    }

    public TextSearchArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}