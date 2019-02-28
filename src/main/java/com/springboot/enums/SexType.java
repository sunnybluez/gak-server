package com.springboot.enums;

public enum SexType {
    MALE("男"),
    FEMALE("女");

    private String description;

    public String getDescription() {
        return description;
    }

    SexType(String description) {

        this.description = description;
    }

    public static SexType parseCode(String desc){
        for(SexType s:SexType.values()){
            if(s.description.equalsIgnoreCase(desc))return s;
        }
        return null;
    }
}
