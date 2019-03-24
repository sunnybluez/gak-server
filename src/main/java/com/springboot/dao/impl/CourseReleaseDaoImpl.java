package com.springboot.dao.impl;

import com.springboot.dao.CourseReleaseDao;
import com.springboot.dao.impl.jpaRepository.CourseReleaseRepository;
import com.springboot.domain.CourseRelease;
import com.springboot.enums.ApproveState;
import com.springboot.enums.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseReleaseDaoImpl implements CourseReleaseDao {

    @Autowired
    CourseReleaseRepository courseReleaseRepository;


    @Override
    public void addCourseRelease(CourseRelease courseRelease) {
        courseReleaseRepository.save(courseRelease);
    }

    @Override
    public void modifyCourseRelease(CourseRelease courseRelease) {
        courseReleaseRepository.save(courseRelease);

    }

    @Override
    public CourseRelease findById(int id) {
        return courseReleaseRepository.findById(id);
    }

    @Override
    public List<CourseRelease> findAllPassCRByTerm(Term term, ApproveState state) {
        return courseReleaseRepository.findAllByTermAndState(term, ApproveState.PASSED);
    }


}
