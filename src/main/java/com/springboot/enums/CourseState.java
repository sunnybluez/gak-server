package com.springboot.enums;

public enum CourseState {

    BEGIN("开课"),
    REELECT("补选"),
    GENERAL("通选");


    private String description;

    CourseState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
