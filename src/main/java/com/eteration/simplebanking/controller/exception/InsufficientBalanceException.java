package com.eteration.simplebanking.controller.exception;


// This class is a place holder you can change the complete implementation
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Insufficient balance for withdrawal");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}

