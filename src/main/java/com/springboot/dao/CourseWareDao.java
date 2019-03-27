package com.springboot.dao;

import com.springboot.domain.CourseWare;

import java.util.List;

public interface CourseWareDao {

    void addCourseWare(CourseWare courseWare);

    List<CourseWare> findAllByCourseCreateId(int courseCreateId);
}
