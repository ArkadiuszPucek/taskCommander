package com.arcadio.domain.exceptions;

public class ComponentNotFoundException extends RuntimeException{
    public ComponentNotFoundException(String message) {
        super(message);
    }

}