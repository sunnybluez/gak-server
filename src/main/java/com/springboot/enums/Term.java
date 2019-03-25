package com.springboot.enums;

public enum Term {
    SPRING2018("2018春"),
    AUTUMN2018("2018秋"),
    SPRING2019("2019春");

    private String description;

    Term(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Term parseCode(String desc){
        for(Term s:Term.values()){
            if(s.description.equalsIgnoreCase(desc))return s;
        }
        return null;
    }
}
