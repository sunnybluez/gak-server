package com.springboot.service;

public interface ManagerService {

    String approveCourseCreate(int courseCreateId, boolean isApprove);
    String approveCourseRelease(int courseReleaseId, boolean isApprove);


}


