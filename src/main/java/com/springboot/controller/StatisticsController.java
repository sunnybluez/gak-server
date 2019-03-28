package com.springboot.controller;

import com.springboot.annotation.ManagerAuth;
import com.springboot.annotation.StudentAuth;
import com.springboot.annotation.TeacherAuth;
import com.springboot.dao.RegisterUserDao;
import com.springboot.dao.StudentDao;
import com.springboot.dao.TeacherDao;
import com.springboot.domain.*;
import com.springboot.enums.GradeType;
import com.springboot.enums.UserIdentity;
import com.springboot.service.AccountService;
import com.springboot.service.CourseStatisticsService;
import com.springboot.service.StudentService;
import com.springboot.service.TeacherCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "statistics")
public class StatisticsController {

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    RegisterUserDao registerUserDao;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseStatisticsService courseStatisticsService;

    @Autowired
    AccountService accountService;

    @Autowired
    TeacherCourseService teacherCourseService;

    @GetMapping(value = "getAllMyCourseOperation")
    @StudentAuth
    public List<HashMap<String, Object>> getAllMyCourseOperation(int studentId) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        List<StudentOperation> operationList = studentService.getAllMyCourseOperationLog(studentId);
        for (StudentOperation studentOperation : operationList) {
            HashMap<String, Object> operatJson = new HashMap<>();
            operatJson.put("operation", studentOperation.getOperateType().getDescription());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDate = sdf.format(studentOperation.getOperateDate());

            operatJson.put("date", createDate);
            String courseName = courseStatisticsService.getCourseReleaseById(studentOperation.getCourseReleaseId()).getCourseCreate().getName();
            operatJson.put("course", studentOperation.getCourseReleaseId() + "-" + courseName);
            list.add(operatJson);
        }
        return list;
    }

    @GetMapping(value = "getStuAndTeaNum")
    @ManagerAuth
    public List<Integer> getStuAndTeaNum() {
        List<RegisterUser> teachers = registerUserDao.findAllByUserIdentityAndAuthenticated(UserIdentity.TEACHER, true);
        List<RegisterUser> students = registerUserDao.findAllByUserIdentityAndAuthenticated(UserIdentity.STUDENT, true);
        List<Integer> result = new ArrayList<>();
        result.add(students.size());
        result.add(teachers.size());
        return result;
    }

    @GetMapping(value = "getStuTypeNum")
    @ManagerAuth
    public List<Integer> getStuTypeNum() {
        List<Student> freshman = studentDao.findByGradeType(GradeType.FRESHMAN);
        List<Student> sophomore = studentDao.findByGradeType(GradeType.SOPHOMORE);
        List<Student> junior = studentDao.findByGradeType(GradeType.JUNIOR);
        List<Student> senior = studentDao.findByGradeType(GradeType.SENIOR);
        List<Integer> result = new ArrayList<>();
        result.add(freshman.size());
        result.add(sophomore.size());
        result.add(junior.size());
        result.add(senior.size());
        return result;
    }


    @GetMapping(value = "getStudentLoginDetail")
    @ManagerAuth
    public List<Integer> getStudentLoginDetail(){
        return accountService.getRecentSevenLoginNum(UserIdentity.STUDENT);
    }

    @GetMapping(value = "getTeacherLoginDetail")
    @ManagerAuth
    public List<Integer> getTeacherLoginDetail(){
        return accountService.getRecentSevenLoginNum(UserIdentity.TEACHER);
    }

    @GetMapping(value = "getTeacherReleaseCourseLog")
    @TeacherAuth
    public List<HashMap<String, Object>> getTeacherReleaseCourseLog(int teacherId){
        List<HashMap<String, Object>> list = new ArrayList<>();
        List<CourseRelease> list1 = teacherCourseService.getReleaseAndPassCourses(teacherId);
//        List<CourseCreate> list2 = teacherCourseService.getCreateAndPassCourses(teacherId);

        for (CourseRelease courseRelease : list1) {
            HashMap<String, Object> logJson = new HashMap<>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDate = sdf.format(courseRelease.getReleaseDate());
            logJson.put("date", createDate);

            logJson.put("operation", "发布");
            logJson.put("name", courseRelease.getCourseCreate().getName());
            list.add(logJson);
        }
        return list;
    }

    @GetMapping(value = "getTeacherCreateCourseLog")
    @TeacherAuth
    public List<HashMap<String, Object>> getTeacherCreateCourseLog(int teacherId){
        List<HashMap<String, Object>> list = new ArrayList<>();
        List<CourseCreate> list1 = teacherCourseService.getCreateAndPassCourses(teacherId);

        for (CourseCreate courseCreate : list1) {
            HashMap<String, Object> logJson = new HashMap<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDate = sdf.format(courseCreate.getCreateDate());
            logJson.put("date", createDate);

            logJson.put("operation", "创建");
            logJson.put("name", courseCreate.getName());
            list.add(logJson);
        }
        return list;


    }

}
