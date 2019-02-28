package com.springboot.enums;

public enum Term {
    SPRING2018("学期2018春"),
    AUTUMN2018("学期2018秋"),
    SPRING2019("续期2019春"),
    AUTUMN2019("学期2019秋"),;

    private String description;

    Term(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
