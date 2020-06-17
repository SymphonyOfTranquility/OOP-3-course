package com.lab3.demo.exception;

public class ClientNotExists extends RuntimeException {
    public ClientNotExists(String message) { super(message); }
}
