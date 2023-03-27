package com.transtour.kernel.domain.exception;

public class DBQueryException extends RuntimeException {

    public DBQueryException() {
        super("An error occurred while executing query.");

    }
}
