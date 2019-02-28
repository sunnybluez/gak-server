package com.springboot.enums;

public enum UserIdentity {
    STUDENT("学生"),
    TEACHER("老师"),
    MANAGER("管理员");


    private String description;

    UserIdentity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    }
