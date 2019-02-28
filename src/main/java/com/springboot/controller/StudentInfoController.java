package com.springboot.controller;


import com.springboot.annotation.StudentAuth;
import com.springboot.domain.RegisterUser;
import com.springboot.domain.Student;
import com.springboot.enums.GradeType;
import com.springboot.enums.SexType;
import com.springboot.service.RegisterUserService;
import com.springboot.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "studentInfo")
public class StudentInfoController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RegisterUserService registerUserService;


    @RequestMapping(value = "getStudentId")
    @StudentAuth
    public Integer getStudentId(String email) {
        Student student = studentService.findByEmail(email);
        return student.getId();
    }

    @RequestMapping(value = "getStudentDetail")
    @StudentAuth
    public Map<String,Object> getStudentInfo(int id) {
        Student student = studentService.findById(id);
        Map<String, Object> studentDetail = new HashMap<>();
        studentDetail.put("email", student.getEmail());
        studentDetail.put("name", student.getName());
        studentDetail.put("studentNum", student.getStudentNum());
        studentDetail.put("sex", student.getSex().getDescription());
        studentDetail.put("age", student.getAge());
        studentDetail.put("phoneNum", student.getPhoneNum());
        studentDetail.put("grade", student.getGrade().getDescription());
        return studentDetail;
    }




    @RequestMapping(value = "updateStudentInfo")
    @StudentAuth
    public String updateStudentInfo(int id, String name, int age,
                                    int studentNum, String sex, int phoneNum,
                                    String grade) {

        Student studentModify = studentService.findById(id);
        studentModify.setName(name);
        studentModify.setAge(age);
        studentModify.setStudentNum(studentNum);
        studentModify.setSex(SexType.parseCode(sex));
        studentModify.setPhoneNum(phoneNum);
        studentModify.setGrade(GradeType.parseCode(grade));
        studentService.modifyStudent(studentModify);

        return "修改成功";
    }

    @RequestMapping(value = "cancel")
    @StudentAuth
    public String cancelStudentAccount(int id) {
        Student studentCancel = studentService.findById(id);
        studentCancel.setCancelled(true);
        studentService.modifyStudent(studentCancel);

        String email = studentCancel.getEmail();
        RegisterUser registerUser = registerUserService.findByEmail(email);
        registerUser.setAuthenticated(false);
        registerUserService.modifyRegisterUser(registerUser);

        return "true";
    }
}
