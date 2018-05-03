package com.telstra.assignment.solution.exception;

/**
 * Created by aditya.khajuria on 4/23/2018.
 */
public class OneArrayNotFoundException extends RuntimeException {

    private String errorMessage;

    public OneArrayNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
