package com.adevinta.textsearch.exception;

public class TextSearchFileException extends TextSearchException {
    public TextSearchFileException(String message) {
        super(message);
    }

    public TextSearchFileException(String message, Throwable cause) {
        super(message, cause);
    }
}