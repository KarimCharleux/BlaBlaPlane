package com.example.blablaplane.Exceptions;

public class NoPlacesAvailableException extends RuntimeException {
    public NoPlacesAvailableException(String errorMessage) {
        super(errorMessage);
    }
}
