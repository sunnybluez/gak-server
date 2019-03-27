package com.springboot.service;


import com.springboot.domain.CourseRelease;
import com.springboot.domain.Homework;
import com.springboot.domain.StudentWork;

import java.util.List;

public interface TaskService {

    List<Homework> getAllHomework(int courseReleaseId);

    String addHomework(int courseReleaseId, String title, String description, int finishTime);



    String addStudentWork(String path, int homeworkId, int courseReleaseId, int studentId,String name);

    List<StudentWork> getAllMyStudentWork(int studentId, int courseReleaseId);

    StudentWork getSingleStudentWork(int studentId,int courseReleaseId, int homeworkId);

    List<StudentWork> getAllStudentWork(int homeworkId);

    void setCourseReleaseScoreExcelPath(CourseRelease courseRelease);
}
