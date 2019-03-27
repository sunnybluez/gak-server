package com.springboot.service.impl;

import com.springboot.dao.CourseReleaseDao;
import com.springboot.dao.CourseSelectDao;
import com.springboot.dao.HomeworkDao;
import com.springboot.dao.StudentWorkDao;
import com.springboot.domain.CourseRelease;
import com.springboot.domain.CourseSelect;
import com.springboot.domain.Homework;
import com.springboot.domain.StudentWork;
import com.springboot.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    HomeworkDao homeworkDao;

    @Autowired
    CourseReleaseDao courseReleaseDao;

    @Autowired
    CourseSelectDao courseSelectDao;

    @Autowired
    StudentWorkDao studentWorkDao;

    @Override
    public List<Homework> getAllHomework(int courseReleaseId) {
        return homeworkDao.findAllHomeWorkByCourseReleaseId(courseReleaseId);
    }

    @Override
    public String addHomework(int courseReleaseId, String title, String description, int finishTime) {
        CourseRelease courseRelease = courseReleaseDao.findById(courseReleaseId);
        Calendar now =Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE,now.get(Calendar.DATE)+finishTime);


        Homework homework = new Homework(description, title, now.getTime(), courseRelease);

        homeworkDao.addHomeWork(homework);
        return "添加成功";
    }

    @Override
    public void setWorkScore(StudentWork studentWork, double score) {
        studentWork.setScore(score);
        studentWorkDao.modifyStudentWork(studentWork);
    }

    @Override
    public String addStudentWork(String path, int homeworkId, int courseReleaseId, int studentId,String name) {
        CourseSelect courseSelect = courseSelectDao.findByStudentIdAndCourseReleaseId(studentId, courseReleaseId);
        Homework homework = homeworkDao.findById(homeworkId);
        StudentWork studentWork = new StudentWork(name,path, courseSelect, homework);
        studentWorkDao.addStudentWork(studentWork);
        return "上传成功";
    }

    @Override
    public List<StudentWork> getAllMyStudentWork(int studentId, int courseReleaseId) {
        int courseSelectId = courseSelectDao.findByStudentIdAndCourseReleaseId(studentId, courseReleaseId).getId();
        return studentWorkDao.findAllByCourseSelectId(courseSelectId);
    }

    @Override
    public StudentWork getSingleStudentWork(int studentId, int courseReleaseId, int homeworkId) {
        CourseSelect courseSelect = courseSelectDao.findByStudentIdAndCourseReleaseId(studentId, courseReleaseId);
        return studentWorkDao.findByCourseSelectIdIdAndHomeWorkId(courseSelect.getId(), homeworkId);

    }

    @Override
    public List<StudentWork> getAllStudentWork(int homeworkId) {
        return studentWorkDao.findAllStudentWorkByHomeworkId(homeworkId);
    }


}
