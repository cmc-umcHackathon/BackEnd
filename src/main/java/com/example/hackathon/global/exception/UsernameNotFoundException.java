package com.example.hackathon.global.exception;

public class UsernameNotFoundException  extends RuntimeException {
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
