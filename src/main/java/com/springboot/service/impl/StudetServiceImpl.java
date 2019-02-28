package com.springboot.service.impl;

import com.springboot.dao.StudentDao;
import com.springboot.domain.Student;
import com.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudetServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public Student findByEmail(String email) {
        return studentDao.findByEmail(email);
    }

    @Override
    public Student findById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public void modifyStudent(Student student) {
        studentDao.modifyStudent(student);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }
}
