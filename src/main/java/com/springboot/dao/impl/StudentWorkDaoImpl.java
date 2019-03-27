package com.springboot.dao.impl;

import com.springboot.dao.StudentWorkDao;
import com.springboot.dao.impl.jpaRepository.StudentWorkRepository;
import com.springboot.domain.StudentWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentWorkDaoImpl implements StudentWorkDao {

    @Autowired
    StudentWorkRepository studentWorkRepository;


    @Override
    public void addStudentWork(StudentWork studentWork) {
        studentWorkRepository.save(studentWork);
    }

    @Override
    public List<StudentWork> findAllByCourseSelectId(int courseSelectId) {
        return studentWorkRepository.findAllByCourseSelectId(courseSelectId);
    }

    @Override
    public StudentWork findByCourseSelectIdIdAndHomeWorkId(int courseSelectId, int homeworkId) {
        return studentWorkRepository.findByCourseSelectIdAndHomeworkId(courseSelectId, homeworkId);
    }

    @Override
    public List<StudentWork> findAllStudentWorkByHomeworkId(int homeworkId) {
        return studentWorkRepository.findAllByHomeworkId(homeworkId);
    }

    @Override
    public void modifyStudentWork(StudentWork studentWork) {
        studentWorkRepository.save(studentWork);
    }

}
