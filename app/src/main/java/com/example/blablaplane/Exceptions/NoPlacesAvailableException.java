package com.example.blablaplane.Exceptions;

public class NoPlacesAvailable extends Exception {
    public NoPlacesAvailable(String errorMessage) {
        super(errorMessage);
    }
}
