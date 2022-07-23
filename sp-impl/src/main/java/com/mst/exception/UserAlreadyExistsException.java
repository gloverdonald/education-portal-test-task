package com.mst.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends HttpServiceException {
    public UserAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "Email is already taken");
    }
}