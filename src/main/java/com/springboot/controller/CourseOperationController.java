package com.springboot.controller;

import com.springboot.annotation.StudentAuth;
import com.springboot.annotation.TeacherAuth;
import com.springboot.enums.GradeType;
import com.springboot.enums.Term;
import com.springboot.service.StudentCourseService;
import com.springboot.service.TeacherCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "courseOperation")
public class CourseOperationController {

    @Autowired
    TeacherCourseService teacherCourseService;

    @Autowired
    StudentCourseService studentCourseService;

    @RequestMapping(value = "createCourse")
    @TeacherAuth
    @ResponseBody
    public String createCourse(@RequestBody Map map) {
        int teacherId = (int) map.get("teacherId");
        String courseName = (String) map.get("courseName");
        String courseDescription = (String) map.get("courseDescription");
        return teacherCourseService.createCourse(teacherId, courseName, courseDescription);
    }

    @RequestMapping(value = "releaseCourse")
    @TeacherAuth
    @ResponseBody
    public String releaseCourse(@RequestBody Map map) {

        int courseCreateId = (int) map.get("courseCreateId");
        String studentGradeType = (String) map.get("studentGradeType");
        int limitNum = (int) map.get("limitNum");
        String term = (String) map.get("term");

        GradeType studentType = GradeType.parseCode(studentGradeType);
        Term openTerm = Term.parseCode(term);
        return teacherCourseService.releaseCourse(courseCreateId, studentType, limitNum, openTerm);
    }

    @RequestMapping(value = "beginClass")
    @TeacherAuth
    @ResponseBody
    public String beginClass(@RequestBody Map map) {
        int courseReleaseId = (int) map.get("courseReleaseId");
        return teacherCourseService.beginClass(courseReleaseId);
    }

    @RequestMapping(value = "officialBeginClass")
    @TeacherAuth
    @ResponseBody
    public String officialBeginClass(@RequestBody Map map) {
        int courseReleaseId = (int) map.get("courseReleaseId");
        return teacherCourseService.officialBeginClass(courseReleaseId);
    }


    @PostMapping(value = "selectCourse")
    @StudentAuth
    @ResponseBody
    public String selectCourse(@RequestBody Map map) {
        int studentId = (int) map.get("studentId");
        int courseReleaseId = (int) map.get("courseReleaseId");
        return studentCourseService.selectCourse(studentId, courseReleaseId);
    }

    @RequestMapping(value = "dropCourse")
    @StudentAuth
    @ResponseBody
    public String dropCourse(@RequestBody Map map) {
        int studentId = (int) map.get("studentId");
        int courseReleaseId = (int) map.get("courseReleaseId");
        return studentCourseService.dropCourse(studentId, courseReleaseId);
    }

    @PostMapping(value = "reelectCourse")
    @StudentAuth
    @ResponseBody
    public String reelectCourse(@RequestBody Map map) {
        int studentId = (int) map.get("studentId");
        int courseReleaseId = (int) map.get("courseReleaseId");
        return studentCourseService.reelectCourse(studentId, courseReleaseId);
    }

}
