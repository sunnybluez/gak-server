package com.springboot.controller;


import com.springboot.annotation.StudentAuth;
import com.springboot.enums.GradeType;
import com.springboot.enums.SexType;
import com.springboot.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "studentInfo")
public class StudentInfoController {

    @Autowired
    private StudentService studentService;



    @RequestMapping(value = "getStudentId")
    @StudentAuth
    public Integer getStudentId(String email) {

        return studentService.getStudentId(email);
    }

    @RequestMapping(value = "getStudentDetail")
    @StudentAuth
    public Map<String,Object> getStudentInfo(int id) {
        return studentService.getStudentInfo(id);
    }


    @RequestMapping(value = "updateStudentInfo")
    @StudentAuth
    public String updateStudentInfo(int id, String name, int age, int studentNum, String sex, int phoneNum, String grade) {
        return studentService.updateStudentInfo(id, name, age, studentNum, SexType.parseCode(sex), phoneNum, GradeType.parseCode(grade));
    }

}
