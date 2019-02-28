package com.springboot.dao;

import com.springboot.domain.Teacher;

public interface TeacherDao {

    Teacher findByEmail(String email);

    void modifyTeacher(Teacher teacher);

    void addTeacher(Teacher teacher);

    Teacher findById(int id);

}
