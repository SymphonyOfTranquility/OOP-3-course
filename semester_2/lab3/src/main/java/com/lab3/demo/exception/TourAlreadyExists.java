package com.lab3.demo.exception;

public class TourAlreadyExists extends RuntimeException{
    public TourAlreadyExists(String message) {
        super(message);
    }
}
