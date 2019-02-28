package com.springboot.dao;

import com.springboot.domain.Student;

public interface StudentDao {

    Student findByEmail(String email);

    void modifyStudent(Student student);

    void addStudent(Student student);

    Student findById(int id);

}
