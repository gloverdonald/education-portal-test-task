package com.mst.exception;

public class UserNotFoundException extends ModelNotFoundException {
    public UserNotFoundException() {
        super("User not found");
    }
}