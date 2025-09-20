package com.shelfwise.backend.common.exception;

public class UnpaidFineException extends RuntimeException{
    public UnpaidFineException(String message) {
        super(message);
    }
}
