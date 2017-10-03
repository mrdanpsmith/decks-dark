package com.siirush.decksdark;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDeckException extends RuntimeException {
    public InvalidDeckException(String message) {
        super(message);
    }
    public InvalidDeckException(String message, Throwable cause) {
        super(message, cause);
    }
}
