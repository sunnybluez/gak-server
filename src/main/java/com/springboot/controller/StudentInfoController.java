package com.springboot.controller;


import com.springboot.annotation.StudentAuth;
import com.springboot.enums.GradeType;
import com.springboot.enums.SexType;
import com.springboot.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "studentInfo")
public class StudentInfoController {

    @Autowired
    private StudentService studentService;





    @GetMapping(value = "getStudentDetail")
    @StudentAuth
    public Map<String,Object> getStudentInfo(int id) {
        return studentService.getStudentInfo(id);
    }


    @PostMapping(value = "updateStudentInfo")
    @StudentAuth
    public String updateStudentInfo(@RequestBody Map map) {
        int id = (int) map.get("id");
        String name = (String) map.get("name");
        int age = (int) map.get("age");
        String sex = (String) map.get("sex");
        int phoneNum = (int) map.get("phoneNum");
        String grade = (String) map.get("grade");

        return studentService.updateStudentInfo(id, name, age, SexType.parseCode(sex), phoneNum, GradeType.parseCode(grade));
    }

}
