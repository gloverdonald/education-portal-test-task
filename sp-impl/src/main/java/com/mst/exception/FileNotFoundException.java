package com.mst.exception;

public class FileNotFoundException extends ModelNotFoundException {

    public FileNotFoundException() {
        super("File Not Found");
    }
}