package com.springboot.service;

import com.springboot.domain.Student;

public interface StudentService {

    Student findByEmail(String email);

    Student findById(int id);

    void modifyStudent(Student student);

    void addStudent(Student student);


}
