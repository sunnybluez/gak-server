package com.springboot.service;

import com.springboot.domain.CourseCreate;
import com.springboot.enums.GradeType;
import com.springboot.enums.Term;

import java.util.List;

public interface TeacherCourseService {

    String createCourse(int teacherId, String name, String description);

    List<CourseCreate> getCreateAndPassCourses(int teacherId);

    String releaseCourse(int courseCreateId, GradeType gradeType, int limitNum, Term term);

    String beginClass(int courseReleaseId);

    String officialBeginClass(int courseReleaseId);


}
