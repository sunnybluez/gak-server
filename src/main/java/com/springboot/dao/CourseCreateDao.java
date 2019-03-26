package com.springboot.dao;

import com.springboot.domain.CourseCreate;
import com.springboot.enums.ApproveState;

import java.util.List;

public interface CourseCreateDao {

    void addCourseCreate(CourseCreate courseCreate);

    void modifyCourseCreate(CourseCreate courseCreate);

    CourseCreate findById(int id);

    List<CourseCreate> findAllCreateCourseByTIdAndAppState(int teacherId, ApproveState state);

//    List<CourseCreate> getAllCreateCourseByTidAndApprovaeState(int teacherId,App)

//    List<>
}
