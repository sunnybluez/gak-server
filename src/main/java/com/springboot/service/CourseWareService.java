package com.springboot.service;

import com.springboot.domain.CourseWare;

import java.util.List;

public interface CourseWareService {

    void addCourseWare(CourseWare courseWare);

    List<CourseWare> getAllCourseWare(int courseCreateId);

}
