package com.springboot.service.impl;

import com.springboot.dao.CourseWareDao;
import com.springboot.domain.CourseWare;
import com.springboot.service.CourseWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseWareServiceImpl implements CourseWareService {

    @Autowired
    CourseWareDao courseWareDao;

    @Override
    public void addCourseWare(CourseWare courseWare) {
        courseWareDao.addCourseWare(courseWare);
    }

    @Override
    public List<CourseWare> getAllCourseWare(int courseCreateId) {
        return courseWareDao.findAllByCourseCreateId(courseCreateId);
    }
}
