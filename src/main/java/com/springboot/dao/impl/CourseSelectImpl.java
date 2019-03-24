package com.springboot.dao.impl;

import com.springboot.dao.CourseSelectDao;
import com.springboot.dao.impl.jpaRepository.CourseSelectRepository;
import com.springboot.domain.CourseSelect;
import com.springboot.enums.SelectState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseSelectImpl implements CourseSelectDao {

    @Autowired
    CourseSelectRepository courseSelectRepository;

    @Override
    public void addCourseSelect(CourseSelect courseSelect) {
        courseSelectRepository.save(courseSelect);
    }

    @Override
    public List<CourseSelect> findAllCSByCRIdAndState(int courseReleaseId, SelectState selectState) {
        return courseSelectRepository.findAllByCourseReleaseIdAndState(courseReleaseId, selectState);
    }

    @Override
    public void modifyCourseSelect(CourseSelect courseSelect) {
        courseSelectRepository.save(courseSelect);
    }

    @Override
    public CourseSelect findById(int courseSelectedId) {
        return courseSelectRepository.findById(courseSelectedId);
    }
}
