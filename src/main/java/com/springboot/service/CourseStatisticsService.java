package com.springboot.service;

import com.springboot.domain.CourseRelease;

public interface CourseStatisticsService {

    int getSelectNum(int courseReleaseId);


    int getOngoingNum(int courseReleaseId);

    CourseRelease getCourseReleaseById(int courseReleaseId);
}
