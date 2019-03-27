package com.springboot.service;

import com.springboot.enums.SexType;

import java.util.Map;

public interface TeacherService {

    Integer getTeacherId(String email);

    Map<String, Object> getTeacherInfo(int id);

    String updateTeacherInfo(int id, String name, int age, SexType sex, int phoneNum);



}
