package com.arcadio.domain.exceptions;

public class NoAddressesFoundException extends RuntimeException{

    public NoAddressesFoundException(String message) {
        super(message);
    }
}
