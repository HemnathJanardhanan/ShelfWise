package com.shelfwise.backend.common.exception;

public class BorrowLimitExceededException extends RuntimeException {
    public BorrowLimitExceededException(String message) {
        super(message);
    }
}
