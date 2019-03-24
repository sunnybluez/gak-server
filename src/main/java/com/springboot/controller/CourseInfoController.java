package com.springboot.controller;

import com.springboot.domain.CourseRelease;
import com.springboot.enums.Term;
import com.springboot.service.CourseStatisticsService;
import com.springboot.service.StudentCourseService;
import com.springboot.service.TeacherCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "courseInfo")
public class CourseInfoController {

    @Autowired
    TeacherCourseService teacherCourseService;

    @Autowired
    StudentCourseService studentCourseService;

    @Autowired
    CourseStatisticsService courseStatisticsService;

    //term必须是 SPRING2019的形式
    @RequestMapping(value = "getAllMyOnCourseByTerm")
//    @StudentAuth
    public HashMap<Integer, String> getAllMyOnCourseByTerm(int studentId, String term) {
        HashMap<Integer, String> result = new HashMap<>();
        Term termIn = Term.valueOf(term);
        List<CourseRelease> courseReleaseList = studentCourseService.getMyOnCoursesByTerm(studentId, termIn);


        for (CourseRelease courseRelease : courseReleaseList) {
            String name = courseRelease.getCourseCreate().getName();
            int id = courseRelease.getId();
            result.put(id, name);
        }

        return result;

    }

    @RequestMapping(value = "getAllCanSelectCourseByTerm")
//    @StudentAuth
    public List<HashMap<String,Object>> getAllCanSelectCourseByTerm(int studentId, String term) {
        System.out.println(studentId+term);
        List<HashMap<String, Object>> result = new ArrayList<>();
        Term termIn = Term.valueOf(term);
        List<CourseRelease> courseReleaseList = studentCourseService.getAllCanSelectCourseByTerm(studentId, termIn);
        System.out.println("大小"+courseReleaseList.size());
        for (CourseRelease courseRelease : courseReleaseList) {
            HashMap<String, Object> courseJson = new HashMap<>();
            courseJson.put("name", courseRelease.getCourseCreate().getName());
            courseJson.put("teacher", courseRelease.getTeacher().getName());
            courseJson.put("limitNum", courseRelease.getLimitNum());
            courseJson.put("description", courseRelease.getCourseCreate().getDescription());
            courseJson.put("id", courseRelease.getId());

            int selectNum = courseStatisticsService.getSelectNum(courseRelease.getId());
            courseJson.put("selectNum", selectNum);
            result.add(courseJson);
        }
        System.out.println(1);
        return result;

    }
}
