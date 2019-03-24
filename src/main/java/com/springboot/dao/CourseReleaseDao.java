package com.springboot.dao;

import com.springboot.domain.CourseRelease;
import com.springboot.enums.ApproveState;
import com.springboot.enums.CourseState;
import com.springboot.enums.Term;

import java.util.List;

public interface CourseReleaseDao {

    void addCourseRelease(CourseRelease courseRelease);

    void modifyCourseRelease(CourseRelease courseRelease);

    CourseRelease findById(int id);

    List<CourseRelease> findAllCRByTermAndState(Term term, ApproveState state);

    List<CourseRelease> findAllCRByTermAndCourseState(Term term, CourseState courseState);



}
