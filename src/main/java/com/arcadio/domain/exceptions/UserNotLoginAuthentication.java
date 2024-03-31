package com.arcadio.domain.exceptions;

public class UserNotLoginAuthentication extends  RuntimeException{
    public UserNotLoginAuthentication(String message) {
        super(message);
    }
}
