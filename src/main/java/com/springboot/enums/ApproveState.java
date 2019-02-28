package com.springboot.enums;

public enum ApproveState {
    PASSED("通过"),
    FAILED("未通过"),
    WAITING("等待审批");


    private String description;

    ApproveState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
