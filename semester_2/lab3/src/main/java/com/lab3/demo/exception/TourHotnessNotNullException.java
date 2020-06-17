package com.lab3.demo.exception;

public class TourHotnessNotNullException extends RuntimeException {
    public TourHotnessNotNullException() { super("Tour id is required"); }
}
