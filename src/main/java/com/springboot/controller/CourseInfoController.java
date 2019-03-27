package com.springboot.controller;

import com.springboot.annotation.StudentAuth;
import com.springboot.annotation.TeacherAuth;
import com.springboot.domain.CourseCreate;
import com.springboot.domain.CourseRelease;
import com.springboot.enums.Term;
import com.springboot.service.CourseStatisticsService;
import com.springboot.service.StudentCourseService;
import com.springboot.service.TeacherCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping(value = "getAllMyOnCourseByTerm")
    @StudentAuth
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

    @GetMapping(value = "getAllCanSelectCourseByTerm")
    @StudentAuth
    public List<HashMap<String, Object>> getAllCanSelectCourseByTerm(int studentId, String term) {


        List<HashMap<String, Object>> result = new ArrayList<>();
        Term termIn = Term.valueOf(term);
        List<CourseRelease> courseReleaseList = studentCourseService.getAllCanSelectCourseByTerm(studentId, termIn);

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

        return result;

    }


    @GetMapping(value = "getAllCanReelectCourseByTerm")
    @StudentAuth
    public List<HashMap<String, Object>> getAllCanReelectCourseByTerm(int studentId, String term) {


        List<HashMap<String, Object>> result = new ArrayList<>();
        Term termIn = Term.valueOf(term);
        List<CourseRelease> courseReleaseList = studentCourseService.getAllCanReelectCourseByTerm(studentId, termIn);

        for (CourseRelease courseRelease : courseReleaseList) {

            HashMap<String, Object> courseJson = new HashMap<>();
            courseJson.put("name", courseRelease.getCourseCreate().getName());
            courseJson.put("teacher", courseRelease.getTeacher().getName());
            courseJson.put("description", courseRelease.getCourseCreate().getDescription());
            courseJson.put("limitNum", courseRelease.getLimitNum());
            courseJson.put("id", courseRelease.getId());
            int selectNum = courseStatisticsService.getOngoingNum(courseRelease.getId());
            courseJson.put("selectNum", selectNum);
            result.add(courseJson);
        }

        return result;

    }

    @GetMapping(value = "getAllSelectedCourseByTerm")
    @StudentAuth
    public List<HashMap<String, Object>> getAllSelectedCourseByTerm(int studentId, String term) {


        List<HashMap<String, Object>> result = new ArrayList<>();
        Term termIn = Term.valueOf(term);
        List<CourseRelease> courseReleaseList = studentCourseService.getAllSelectedCourseByTerm(studentId, termIn);

        for (CourseRelease courseRelease : courseReleaseList) {
            HashMap<String, Object> courseJson = new HashMap<>();
            courseJson.put("name", courseRelease.getCourseCreate().getName());
            courseJson.put("teacher", courseRelease.getTeacher().getName());
            courseJson.put("description", courseRelease.getCourseCreate().getDescription());
            courseJson.put("id", courseRelease.getId());
            courseJson.put("limitNum", courseRelease.getLimitNum());
            int selectNum = courseStatisticsService.getSelectNum(courseRelease.getId());
            courseJson.put("selectNum", selectNum);
            result.add(courseJson);
        }

        return result;

    }

    @GetMapping(value = "getAllFailedCourseByTerm")
    @StudentAuth
    public List<HashMap<String, Object>> getAllFailedCourseByTerm(int studentId, String term) {


        List<HashMap<String, Object>> result = new ArrayList<>();
        Term termIn = Term.valueOf(term);
        List<CourseRelease> courseReleaseList = studentCourseService.getAllFailedCourseByTerm(studentId, termIn);

        for (CourseRelease courseRelease : courseReleaseList) {
            HashMap<String, Object> courseJson = new HashMap<>();
            courseJson.put("name", courseRelease.getCourseCreate().getName());
            courseJson.put("description", courseRelease.getCourseCreate().getDescription());
            courseJson.put("teacher", courseRelease.getTeacher().getName());
            courseJson.put("id", courseRelease.getId());
            courseJson.put("limitNum", courseRelease.getLimitNum());
            int selectNum = courseStatisticsService.getOngoingNum(courseRelease.getId());
            courseJson.put("selectNum", selectNum);
            result.add(courseJson);
        }

        return result;

    }

    @GetMapping(value = "getAllOngoingCourseByTerm")
    @StudentAuth
    public List<HashMap<String, Object>> getAllOngoingCourseByTerm(int studentId, String term) {


        List<HashMap<String, Object>> result = new ArrayList<>();
        Term termIn = Term.valueOf(term);
        List<CourseRelease> courseReleaseList = studentCourseService.getAllOngoingCourseByTerm(studentId, termIn);

        for (CourseRelease courseRelease : courseReleaseList) {
            HashMap<String, Object> courseJson = new HashMap<>();
            courseJson.put("name", courseRelease.getCourseCreate().getName());
            courseJson.put("teacher", courseRelease.getTeacher().getName());
            courseJson.put("description", courseRelease.getCourseCreate().getDescription());
            courseJson.put("id", courseRelease.getId());

            courseJson.put("limitNum", courseRelease.getLimitNum());
            int selectNum = courseStatisticsService.getOngoingNum(courseRelease.getId());
            courseJson.put("selectNum", selectNum);
            result.add(courseJson);
        }

        return result;

    }

    @GetMapping(value = "getAllMyCreatePassCourseCreate")
    @TeacherAuth
    public List<HashMap<String, Object>> getAllMyCreatePassCourseCreate(int teacherId) {

        List<HashMap<String, Object>> result = new ArrayList<>();
        List<CourseCreate> courseCreateList = teacherCourseService.getCreateAndPassCourses(teacherId);

        for (CourseCreate courseCreate : courseCreateList) {
            HashMap<String, Object> courseCreateJson = new HashMap<>();
            courseCreateJson.put("id", courseCreate.getId());
            courseCreateJson.put("name", courseCreate.getName());
            courseCreateJson.put("description", courseCreate.getDescription());
            result.add(courseCreateJson);
        }
        return result;


    }


    @GetMapping(value = "getAllWaitingOrFailedCourseCreate")
    @TeacherAuth
    public List<HashMap<String, Object>> getAllWaitingOrFailedCourseCreate(int teacherId) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        List<CourseCreate> courseCreateList = teacherCourseService.getAllWaitingOrFailedCourseCreate(teacherId);

        for (CourseCreate courseCreate : courseCreateList) {
            HashMap<String, Object> courseCreateJson = new HashMap<>();
            courseCreateJson.put("id", courseCreate.getId());

            courseCreateJson.put("name", courseCreate.getName());
            courseCreateJson.put("approveState", courseCreate.getState().getDescription());
            result.add(courseCreateJson);
        }
        return result;
    }

    @GetMapping(value = "getAllWaitingOrFailedCourseRelease")
    @TeacherAuth
    public List<HashMap<String, Object>> getAllWaitingOrFailedCourseRelease(int teacherId, String term) {
        Term termCu = Term.SPRING2019;
        if (term != null) termCu = Term.valueOf(term);

        List<HashMap<String, Object>> result = new ArrayList<>();
        List<CourseRelease> courseReleaseList = teacherCourseService.getAllWaitingOrFailedCourseRelease(teacherId, termCu);

        for (CourseRelease courseRelease : courseReleaseList) {

            HashMap<String, Object> courseReleaseJson = new HashMap<>();
            courseReleaseJson.put("id", courseRelease.getId());
            courseReleaseJson.put("name", courseRelease.getCourseCreate().getName());
            courseReleaseJson.put("approveState", courseRelease.getState().getDescription());
            result.add(courseReleaseJson);
        }
        return result;
    }

    @GetMapping(value = "getAllGeneralCourse")
    @TeacherAuth
    public List<HashMap<String, Object>> getAllGeneralCourse(int teacherId, String term) {
        Term termCu = Term.SPRING2019;
        if (term != null) termCu = Term.valueOf(term);

        List<HashMap<String, Object>> result = new ArrayList<>();
        List<CourseRelease> courseReleaseList = teacherCourseService.getAllGeneralCourse(teacherId, termCu);

        for (CourseRelease courseRelease : courseReleaseList) {
            HashMap<String, Object> courseReleaseJson = new HashMap<>();
            courseReleaseJson.put("id", courseRelease.getId());
            courseReleaseJson.put("name", courseRelease.getCourseCreate().getName());
            courseReleaseJson.put("limitNum", courseRelease.getLimitNum());
            int count = courseStatisticsService.getSelectNum(courseRelease.getId());
            courseReleaseJson.put("selectNum", count);
            result.add(courseReleaseJson);
        }
        return result;
    }

    @GetMapping(value = "getAllReelectCourse")
    @TeacherAuth
    public List<HashMap<String, Object>> getAllReelectCourse(int teacherId, String term) {
        Term termCu = Term.SPRING2019;
        if (term != null) termCu = Term.valueOf(term);

        List<HashMap<String, Object>> result = new ArrayList<>();
        List<CourseRelease> courseReleaseList = teacherCourseService.getAllReelectCourse(teacherId, termCu);

        for (CourseRelease courseRelease : courseReleaseList) {
            HashMap<String, Object> courseReleaseJson = new HashMap<>();
            courseReleaseJson.put("id", courseRelease.getId());
            courseReleaseJson.put("name", courseRelease.getCourseCreate().getName());
            courseReleaseJson.put("limitNum", courseRelease.getLimitNum());
            int count = courseStatisticsService.getOngoingNum(courseRelease.getId());
            courseReleaseJson.put("selectNum", count);
            result.add(courseReleaseJson);
        }
        return result;
    }

    @GetMapping(value = "getAllBeginCourse")
    @TeacherAuth
    public List<HashMap<String, Object>> getAllBeginCourse(int teacherId, String term) {
        Term termCu = Term.SPRING2019;
        if (term != null) termCu = Term.valueOf(term);

        List<HashMap<String, Object>> result = new ArrayList<>();
        List<CourseRelease> courseReleaseList = teacherCourseService.getAllBeginCourse(teacherId, termCu);

        for (CourseRelease courseRelease : courseReleaseList) {
            HashMap<String, Object> courseReleaseJson = new HashMap<>();
            courseReleaseJson.put("id", courseRelease.getId());
            courseReleaseJson.put("name", courseRelease.getCourseCreate().getName());
            int count = courseStatisticsService.getOngoingNum(courseRelease.getId());
            courseReleaseJson.put("selectNum", count);
            result.add(courseReleaseJson);
        }
        return result;
    }

    @GetMapping(value = "getAllBeginClassByTerm")
    @TeacherAuth
    public List<HashMap<String, Object>> getAllBeginClassByTerm(int teacherId, String term) {
        Term termCu = Term.valueOf(term);
        List<HashMap<String, Object>> result = new ArrayList<>();
        List<CourseRelease> courseReleaseList = teacherCourseService.getAllOngoingCourse(teacherId, termCu);

        for (CourseRelease courseRelease : courseReleaseList) {
            HashMap<String, Object> courseReleaseJson = new HashMap<>();
            courseReleaseJson.put("id", courseRelease.getId());
            courseReleaseJson.put("name", courseRelease.getCourseCreate().getName());
            result.add(courseReleaseJson);
        }
        return result;


    }




}
