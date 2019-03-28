package com.springboot.controller;

import com.springboot.annotation.ManagerAuth;
import com.springboot.domain.CourseCreate;
import com.springboot.domain.CourseRelease;
import com.springboot.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "managerWork")
public class ManagerController {        //管理员相关controller

    @Autowired
    ManagerService managerService;

    @GetMapping(value="getAllWaitingCourseCreate")
    @ManagerAuth
    public List<HashMap<String, Object>> getAllWaitingCourseCreate(){
        List<HashMap<String, Object>> result = new ArrayList<>();
        List<CourseCreate> courseCreates = managerService.getAllWaitingCourseCreate();
        for (CourseCreate courseCreate : courseCreates) {
            HashMap<String, Object> has = new HashMap<>();
            has.put("id", courseCreate.getId());
            has.put("name", courseCreate.getName());
            has.put("teacher", courseCreate.getTeacher().getName());
            has.put("description", courseCreate.getDescription());
            result.add(has);
        }
        return result;
    }

    @GetMapping(value="getAllWaitingCourseRelease")
    @ManagerAuth
    public List<HashMap<String, Object>> getAllWaitingCourseRelease(){
        List<HashMap<String, Object>> result = new ArrayList<>();
        List<CourseRelease> courseReleaseList = managerService.getAllWaitingCourseRelease();
        for (CourseRelease courseRelease : courseReleaseList) {
            HashMap<String, Object> has = new HashMap<>();
            has.put("id", courseRelease.getId());
            has.put("name", courseRelease.getCourseCreate().getName());
            has.put("teacher", courseRelease.getTeacher().getName());
            has.put("description", courseRelease.getCourseCreate().getName());
            has.put("term", courseRelease.getTerm().getDescription());
            has.put("grade", courseRelease.getGradeType().getDescription());
            has.put("limitNum", courseRelease.getLimitNum());
            result.add(has);
        }
        return result;
    }


    @RequestMapping(value = "approveCourseCreate")
    @ManagerAuth
    @ResponseBody
    public String approveCourseCreate(@RequestBody Map map) {
        int courseCreateId = (int) map.get("courseCreateId");
        String advice = (String) map.get("advice");
        boolean isApprove = advice.equals("agree") ? true : false;
        managerService.approveCourseCreate(courseCreateId, isApprove);
        return "操作成功";

    }

    @RequestMapping(value = "approveCourseRelease")
    @ManagerAuth
    @ResponseBody
    public String approveCourseRelease(@RequestBody Map map) {
        int courseReleaseId = (int) map.get("courseReleaseId");
        String advice = (String) map.get("advice");
        boolean isApprove = advice.equals("agree") ? true : false;
        managerService.approveCourseRelease(courseReleaseId, isApprove);
        return "操作成功";

    }
}
