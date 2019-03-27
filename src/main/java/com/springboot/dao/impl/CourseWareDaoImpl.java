package com.springboot.dao.impl;

import com.springboot.dao.CourseWareDao;
import com.springboot.dao.impl.jpaRepository.CourseWareRepository;
import com.springboot.domain.CourseWare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseWareDaoImpl implements CourseWareDao {

    @Autowired
    CourseWareRepository courseWareRepository;

    @Override
    public void addCourseWare(CourseWare courseWare) {
        courseWareRepository.save(courseWare);
    }

    @Override
    public List<CourseWare> findAllByCourseCreateId(int courseCreateId) {
        return courseWareRepository.findAllByCourseCreateId(courseCreateId);
    }
}
