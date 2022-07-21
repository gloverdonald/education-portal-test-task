package com.mst.exception;

import org.springframework.http.HttpStatus;

public class FileUploadException extends HttpServiceException{
    public FileUploadException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "File Can't Be Uploaded");
    }
}