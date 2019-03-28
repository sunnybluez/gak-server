package com.springboot.service;

import com.springboot.domain.CourseCreate;
import com.springboot.domain.CourseRelease;

import java.util.List;

public interface ManagerService {

    String approveCourseCreate(int courseCreateId, boolean isApprove);
    String approveCourseRelease(int courseReleaseId, boolean isApprove);

    List<CourseCreate> getAllWaitingCourseCreate();
    List<CourseRelease> getAllWaitingCourseRelease();


}


