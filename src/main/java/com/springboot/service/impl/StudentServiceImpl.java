package com.springboot.service.impl;

import com.springboot.dao.RegisterUserDao;
import com.springboot.dao.StudentDao;
import com.springboot.domain.Student;
import com.springboot.enums.GradeType;
import com.springboot.enums.SexType;
import com.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private RegisterUserDao registerUserDao;

    @Override
    public Integer getStudentId(String email) {
        Student student = studentDao.findByEmail(email);
        return student.getId();
    }

    @Override
    public Map<String, Object> getStudentInfo(int id) {
        Student student = studentDao.findById(id);
        Map<String, Object> studentDetail = new HashMap<>();
        studentDetail.put("email", student.getEmail());
        studentDetail.put("name", student.getName());
//        studentDetail.put("studentNum", student.getStudentNum());
        studentDetail.put("sex", student.getSex().getDescription());
        studentDetail.put("age", student.getAge());
        studentDetail.put("phoneNum", student.getPhoneNum());
        studentDetail.put("grade", student.getGrade().getDescription());
        return studentDetail;
    }

    @Override
    public String updateStudentInfo(int id, String name, int age, SexType sex, int phoneNum, GradeType grade) {
        Student studentModify = studentDao.findById(id);
        studentModify.setName(name);
        studentModify.setAge(age);
//        studentModify.setStudentNum(studentNum);
        studentModify.setSex(sex);
        studentModify.setPhoneNum(phoneNum);
        studentModify.setGrade(grade);
        studentDao.modifyStudent(studentModify);

        return "修改成功";
    }


}
