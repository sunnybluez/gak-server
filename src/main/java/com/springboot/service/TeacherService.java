package com.springboot.service;

import com.springboot.domain.Teacher;

public interface TeacherService {

    Teacher findByEmail(String email);

    void modifyTeacher(Teacher teacher);

    void addTeacher(Teacher teacher);

    Teacher findById(int id);

}
