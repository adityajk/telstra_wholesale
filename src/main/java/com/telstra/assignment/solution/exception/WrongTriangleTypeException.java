package com.telstra.assignment.solution.exception;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
public class WrongTriangleTypeException extends  RuntimeException{

    private String errorMessage;

    public WrongTriangleTypeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
