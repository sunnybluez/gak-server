package com.springboot.service;

import com.springboot.enums.GradeType;
import com.springboot.enums.SexType;

import java.util.Map;

public interface StudentService {

    Integer getStudentId(String email);

    Map<String, Object> getStudentInfo(int id);

    String updateStudentInfo(int id, String name, int age, SexType sex, int phoneNum, GradeType grade);




}
