package com.springboot.service.impl;

import com.springboot.dao.TeacherDao;
import com.springboot.domain.Teacher;
import com.springboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher findByEmail(String email) {
        return teacherDao.findByEmail(email);
    }

    @Override
    public void modifyTeacher(Teacher teacher) {
        teacherDao.modifyTeacher(teacher);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherDao.addTeacher(teacher);
    }

    @Override
    public Teacher findById(int id) {
        return teacherDao.findById(id);
    }
}
