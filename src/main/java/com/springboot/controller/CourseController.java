package com.springboot.controller;

import com.springboot.annotation.TeacherAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "course")
public class CourseController {

    @RequestMapping(value = "createCourse")
    @TeacherAuth
    public String createCourse() {

        return null;
    }

}
