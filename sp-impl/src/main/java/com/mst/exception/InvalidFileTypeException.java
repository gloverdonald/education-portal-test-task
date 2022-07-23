package com.mst.exception;

import org.springframework.http.HttpStatus;

public class InvalidFileTypeException extends HttpServiceException {
    public InvalidFileTypeException() {
        super(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Invalid File Type");
    }
}