package com.springboot.enums;

public enum GradeType {
    FRESHMAN("大一"),
    SOPHOMORE("大二"),
    JUNIOR("大三"),
    SENIOR("大四");

    private String description;

    GradeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static GradeType parseCode(String desc){
        for(GradeType s:GradeType.values()){
            if(s.description.equalsIgnoreCase(desc))return s;
        }
        return null;
    }
}
