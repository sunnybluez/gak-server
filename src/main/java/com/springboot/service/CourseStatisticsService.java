package com.springboot.service;

public interface CourseStatisticsService {

    int getSelectNum(int courseReleaseId);


    int getOngoingNum(int courseReleaseId);
}
