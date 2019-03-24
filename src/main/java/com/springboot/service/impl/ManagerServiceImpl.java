package com.springboot.service.impl;

import com.springboot.dao.CourseCreateDao;
import com.springboot.dao.CourseReleaseDao;
import com.springboot.domain.CourseCreate;
import com.springboot.domain.CourseRelease;
import com.springboot.enums.ApproveState;
import com.springboot.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {


    @Autowired
    CourseCreateDao courseCreateDao;

    @Autowired
    CourseReleaseDao courseReleaseDao;


    @Override
    public String approveCourseCreate(int courseCreateId, boolean isApprove) {
        CourseCreate courseCreate = courseCreateDao.findById(courseCreateId);
        if(isApprove){
            courseCreate.setState(ApproveState.PASSED);
            courseCreateDao.modifyCourseCreate(courseCreate);
            return "审批通过";

        }else {
            courseCreate.setState(ApproveState.FAILED);
            courseCreateDao.modifyCourseCreate(courseCreate);
            return "审批不通过";
        }
    }

    @Override
    public String approveCourseRelease(int courseReleaseId, boolean isApprove) {
        CourseRelease courseRelease = courseReleaseDao.findById(courseReleaseId);
        if(isApprove){
            courseRelease.setState(ApproveState.PASSED);
            courseReleaseDao.modifyCourseRelease(courseRelease);
            return "审批通过";

        }else {
            courseRelease.setState(ApproveState.FAILED);
            courseReleaseDao.modifyCourseRelease(courseRelease);
            return "审批不通过";
        }
    }
}
