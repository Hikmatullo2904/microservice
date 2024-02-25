package com.hikmatullo.cards.exception;

public class CustomBadRequestException extends RuntimeException{
    public CustomBadRequestException(String message) {
        super(message);
    }
}
