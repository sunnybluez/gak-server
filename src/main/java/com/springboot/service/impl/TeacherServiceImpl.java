package com.springboot.service.impl;

import com.springboot.dao.TeacherDao;
import com.springboot.domain.Teacher;
import com.springboot.enums.SexType;
import com.springboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;


    @Override
    public Integer getTeacherId(String email) {
        Teacher teacher = teacherDao.findByEmail(email);
        return teacher.getId();
    }

    @Override
    public Map<String, Object> getTeacherInfo(int id) {
        Teacher teacher = teacherDao.findById(id);
        Map<String, Object> teacherDetail = new HashMap<>();
        teacherDetail.put("email", teacher.getEmail());
        teacherDetail.put("name", teacher.getName());
        teacherDetail.put("studentNum", teacher.getTeacherNum());
        teacherDetail.put("sex", teacher.getSex().getDescription());
        teacherDetail.put("age", teacher.getAge());
        teacherDetail.put("phoneNum", teacher.getPhoneNum());
        return teacherDetail;
    }

    @Override
    public String updateTeacherInfo(int id, String name, int age, int teacherNum, SexType sex, int phoneNum) {
        Teacher teacherModify = teacherDao.findById(id);
        teacherModify.setName(name);
        teacherModify.setAge(age);
        teacherModify.setTeacherNum(teacherNum);
        teacherModify.setSex(sex);
        teacherModify.setPhoneNum(phoneNum);
        teacherDao.modifyTeacher(teacherModify);

        return "修改成功";
    }
}
