package com.springboot.dao.impl;

import com.springboot.dao.CourseReleaseDao;
import com.springboot.dao.impl.jpaRepository.CourseReleaseRepository;
import com.springboot.domain.CourseRelease;
import com.springboot.enums.ApproveState;
import com.springboot.enums.CourseState;
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
    public List<CourseRelease> findAllCRByTermAndState(Term term, ApproveState state) {
        return courseReleaseRepository.findAllByTermAndState(term, ApproveState.PASSED);
    }

    @Override
    public List<CourseRelease> findAllCRByTermAndCourseState(Term term, CourseState courseState) {
        return courseReleaseRepository.findByTermAndCourseState(term, courseState);
    }

    @Override
    public List<CourseRelease> findAllCRBByTermAndTIDAndAppState(int teacherId, Term term, ApproveState approveState) {
        return courseReleaseRepository.findAllByTermAndTeacherIdAndState(term, teacherId, approveState);
    }

    @Override
    public List<CourseRelease> findAllCRBByTermAndTIDAndCouState(int teacherId, Term term, CourseState courseState) {
        return courseReleaseRepository.findAllByTermAndTeacherIdAndCourseState(term, teacherId, courseState);
    }

    @Override
    public List<CourseRelease> findAllByApproveState(ApproveState approveState) {
        return courseReleaseRepository.findAllByState(approveState);
    }

    @Override
    public List<CourseRelease> findAllByTidAndAppState(int teacherId, ApproveState approveState) {
//
        return courseReleaseRepository.findAllByTeacherIdAndState(teacherId, approveState);
    }


}
