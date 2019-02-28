package com.springboot.dao.impl;

import com.springboot.dao.StudentDao;
import com.springboot.dao.impl.jpaRepository.StudentRepository;
import com.springboot.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public void modifyStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }


}
