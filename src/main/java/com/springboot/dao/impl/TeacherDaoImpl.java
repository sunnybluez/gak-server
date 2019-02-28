package com.springboot.dao.impl;

import com.springboot.dao.TeacherDao;
import com.springboot.dao.impl.jpaRepository.TeacherRepository;
import com.springboot.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    @Override
    public void modifyTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher findById(int id) {
        return teacherRepository.findById(id);
    }

}
