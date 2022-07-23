package com.mst.exception;

public class AcademyGroupNotFoundException extends ModelNotFoundException {
    public AcademyGroupNotFoundException() {
        super("Academy Group Not Found");
    }
}
