package com.example.henryshop_api.config.exception;

public class AlreadyExistedUserException extends RuntimeException {
    public AlreadyExistedUserException(String message) {
        super(message);
    }
}