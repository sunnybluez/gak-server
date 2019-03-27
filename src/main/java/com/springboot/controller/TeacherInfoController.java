package com.springboot.controller;


import com.springboot.annotation.TeacherAuth;
import com.springboot.enums.SexType;
import com.springboot.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "teacherInfo")
public class TeacherInfoController {

    @Autowired
    private TeacherService teacherService;


    @GetMapping(value = "getTeacherDetail")
    @TeacherAuth
    public Map<String,Object> getTeacherInfo(int id) {
        return teacherService.getTeacherInfo(id);
    }




    @PostMapping(value = "updateTeacherInfo")
    @TeacherAuth
    public String updateTeacherInfo(@RequestBody Map map ) {
        int id = (int) map.get("id");
        String name = (String) map.get("name");
        int age = (int) map.get("age");
        String sex = (String) map.get("sex");
        int phoneNum = (int) map.get("phoneNum");
        return teacherService.updateTeacherInfo(id, name, age, SexType.parseCode(sex),phoneNum);

    }

}
