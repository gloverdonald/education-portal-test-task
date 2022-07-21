package com.mst.exception;

public class WrongOldPasswordException extends UnauthorizedException {
    public WrongOldPasswordException() {
        super("Wrong old password");
    }
}