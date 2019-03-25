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
    public String createCourse(int teacherId, String name, String description) {

        return teacherCourseService.createCourse(teacherId, name, description);
    }

    @RequestMapping(value = "releaseCourse")
    @TeacherAuth
    public String createCourse(int courseCreateId, String studentGradeType, int limitNum, String term) {
        GradeType studentType = GradeType.parseCode(studentGradeType);
        Term openTerm = Term.valueOf(term.toUpperCase());
        return teacherCourseService.releaseCourse(courseCreateId, studentType, limitNum, openTerm);
    }

    @RequestMapping(value = "beginClass")
    @TeacherAuth
    public String beginClass(int courseReleaseId) {
        return teacherCourseService.beginClass(courseReleaseId);
    }

    @RequestMapping(value = "officialBeginClass")
    @TeacherAuth
    public String officialBeginClass(int courseReleaseId) {
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
    public String drop(int courseReleaseId) {
        return studentCourseService.dropCourse(courseReleaseId);
    }

    @RequestMapping(value = "reelectCourse")
    @StudentAuth
    public String reelectCourse(int studentId, int courseReleaseId) {
        return studentCourseService.reelectCourse(studentId, courseReleaseId);
    }

}
