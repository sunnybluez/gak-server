package com.springboot.enums;

public enum OperateType {


    SELECT("选课"),
    DROP("退课");

    private String description;

    OperateType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
