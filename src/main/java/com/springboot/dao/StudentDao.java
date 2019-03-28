package com.springboot.dao;

import com.springboot.domain.Student;
import com.springboot.enums.GradeType;

import java.util.List;

public interface StudentDao {

    Student findByEmail(String email);

    void modifyStudent(Student student);

    void addStudent(Student student);

    Student findById(int id);

    List<Student> findByGradeType(GradeType gradeType);

}
