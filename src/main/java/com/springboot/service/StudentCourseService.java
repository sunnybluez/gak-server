package com.springboot.service;

import com.springboot.domain.CourseRelease;
import com.springboot.enums.Term;

import java.util.List;

public interface StudentCourseService {

    List<CourseRelease> getAllPassRCourseByTerm(Term term);

    String selectCourse(int studentId, int courseReleaseId);

    String dropCourse(int courseSelectId);

    String reelectCourse(int studentId, int courseReleaseId);

    //某学期学生ongoing的课
    List<CourseRelease> getMyOnCoursesByTerm(int studentId,Term term);

    List<CourseRelease> getAllCanSelectCourseByTerm(int studentId, Term term);

}
