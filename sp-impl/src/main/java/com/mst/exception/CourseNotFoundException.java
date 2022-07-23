package com.mst.exception;

public class CourseNotFoundException extends ModelNotFoundException {
    public CourseNotFoundException() {
        super("Course Not Found");
    }
}
