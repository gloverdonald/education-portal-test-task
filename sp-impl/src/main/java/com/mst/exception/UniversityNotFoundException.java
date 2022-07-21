package com.mst.exception;

public class UniversityNotFoundException extends ModelNotFoundException {
    public UniversityNotFoundException() {
        super("University Not Found");
    }
}
