package com.arcadio.domain.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
