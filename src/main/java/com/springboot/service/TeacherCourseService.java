package com.springboot.service;

import com.springboot.domain.CourseCreate;
import com.springboot.domain.CourseRelease;
import com.springboot.enums.GradeType;
import com.springboot.enums.Term;

import java.util.List;

public interface TeacherCourseService {

    String createCourse(int teacherId, String name, String description);

    List<CourseCreate> getCreateAndPassCourses(int teacherId);

    List<CourseRelease> getReleaseAndPassCourses(int teacherId);

    String releaseCourse(int courseCreateId, GradeType gradeType, int limitNum, Term term);

    String beginClass(int courseReleaseId);

    String officialBeginClass(int courseReleaseId);

    List<CourseCreate> getAllWaitingOrFailedCourseCreate(int teacherId);

    List<CourseRelease> getAllWaitingOrFailedCourseRelease(int teacherId, Term term);

    List<CourseRelease> getAllGeneralCourse(int teacherId, Term term);

    List<CourseRelease> getAllReelectCourse(int teacherId, Term term);

    List<CourseRelease> getAllBeginCourse(int teacherId, Term term);

    List<CourseRelease> getAllOngoingCourse(int teacherId, Term term);

    String groupSendEmail(int courseReleaseId,String content,String subject);



}
