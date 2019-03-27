package com.springboot.dao;

import com.springboot.domain.StudentWork;

import java.util.List;

public interface StudentWorkDao {

    void addStudentWork(StudentWork studentWork);

    List<StudentWork> findAllByCourseSelectId(int courseSelectId);

    StudentWork findByCourseSelectIdIdAndHomeWorkId(int studentId, int homeworkId);

    List<StudentWork> findAllStudentWorkByHomeworkId(int homeworkId);

    void modifyStudentWork(StudentWork studentWork);
}
