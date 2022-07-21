package com.mst.exception;

import org.springframework.http.HttpStatus;

public class FileDownloadException extends HttpServiceException {

    public FileDownloadException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "File Can't Be Downloaded");
    }
}