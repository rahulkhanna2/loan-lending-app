package com.peerlender.lendingengine.domain.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super(String.format("User with id %s not found !!", username));

    }
}
