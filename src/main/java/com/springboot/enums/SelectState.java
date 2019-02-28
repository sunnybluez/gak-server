package com.springboot.enums;

public enum SelectState {
    ONGOING("进行中"),
    FAILED("未选中"),
    RETURNED("已退课"),
    SELECTED("仅选");


    private String description;

    SelectState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
