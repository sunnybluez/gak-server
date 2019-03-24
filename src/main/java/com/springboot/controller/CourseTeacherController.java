package com.springboot.controller;

import com.springboot.annotation.TeacherAuth;
import com.springboot.service.TeacherCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "courseTeacher")
public class CourseTeacherController {

    @Autowired
    TeacherCourseService teacherCourseService;

    @RequestMapping(value = "createCourse")
    @TeacherAuth
    public String createCourse(int teacherId,String name,String description) {

        return teacherCourseService.createCourse(teacherId,name,description);
    }

}
