package com.springboot.controller;


import com.springboot.annotation.TeacherAuth;
import com.springboot.domain.Teacher;
import com.springboot.enums.SexType;
import com.springboot.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "teacherInfo")
public class TeacherInfoController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "getTeacherId")
    @TeacherAuth
    public Integer getTeacherId(String email) {
        Teacher teacher = teacherService.findByEmail(email);
        return teacher.getId();
    }

    @RequestMapping(value = "getTeacherDetail")
    @TeacherAuth
    public Map<String,Object> getTeacherInfo(int id) {
        Teacher teacher = teacherService.findById(id);
        Map<String, Object> teacherDetail = new HashMap<>();
        teacherDetail.put("email", teacher.getEmail());
        teacherDetail.put("name", teacher.getName());
        teacherDetail.put("studentNum", teacher.getTeacherNum());
        teacherDetail.put("sex", teacher.getSex().getDescription());
        teacherDetail.put("age", teacher.getAge());
        teacherDetail.put("phoneNum", teacher.getPhoneNum());
        return teacherDetail;
    }




    @RequestMapping(value = "updateTeacherInfo")
    @TeacherAuth
    public String updateTeacherInfo(int id, String name, int age,
                                    int teacherNum, String sex, int phoneNum) {

        Teacher teacherModify = teacherService.findById(id);
        teacherModify.setName(name);
        teacherModify.setAge(age);
        teacherModify.setTeacherNum(teacherNum);
        teacherModify.setSex(SexType.parseCode(sex));
        teacherModify.setPhoneNum(phoneNum);
        teacherService.modifyTeacher(teacherModify);

        return "修改成功";
    }

}
