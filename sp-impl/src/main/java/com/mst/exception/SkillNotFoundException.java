package com.mst.exception;

public class SkillNotFoundException extends ModelNotFoundException {
    public SkillNotFoundException() {
        super("Skill not found");
    }
}
