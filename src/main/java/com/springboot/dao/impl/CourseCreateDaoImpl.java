package com.springboot.dao.impl;

import com.springboot.dao.CourseCreateDao;
import com.springboot.dao.impl.jpaRepository.CourseCreateRepository;
import com.springboot.domain.CourseCreate;
import com.springboot.enums.ApproveState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseCreateDaoImpl implements CourseCreateDao {

    @Autowired
    CourseCreateRepository courseCreateRepository;

    @Override
    public void addCourseCreate(CourseCreate courseCreate) {
        courseCreateRepository.save(courseCreate);
    }

    @Override
    public void modifyCourseCreate(CourseCreate courseCreate) {
        courseCreateRepository.save(courseCreate);
    }

    @Override
    public CourseCreate findById(int id) {

        return courseCreateRepository.findById(id);
    }

    @Override
    public List<CourseCreate> getCreateAndPassCourse(int teacherId,ApproveState state) {
        return courseCreateRepository.findAllByTeacherIdAndState(teacherId, state);
    }
}
