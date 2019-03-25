package com.springboot.controller;


import com.springboot.annotation.TeacherAuth;
import com.springboot.enums.SexType;
import com.springboot.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "teacherInfo")
public class TeacherInfoController {

    @Autowired
    private TeacherService teacherService;


    @RequestMapping(value = "getTeacherDetail")
    @TeacherAuth
    public Map<String,Object> getTeacherInfo(int id) {
        return teacherService.getTeacherInfo(id);
    }




    @RequestMapping(value = "updateTeacherInfo")
    @TeacherAuth
    public String updateTeacherInfo(int id, String name, int age, int teacherNum, String sex, int phoneNum) {
        return teacherService.updateTeacherInfo(id, name, age, teacherNum, SexType.parseCode(sex),phoneNum);

    }

}
