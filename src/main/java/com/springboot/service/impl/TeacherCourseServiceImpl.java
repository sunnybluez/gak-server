package com.springboot.service.impl;

import com.springboot.dao.CourseCreateDao;
import com.springboot.dao.CourseReleaseDao;
import com.springboot.dao.CourseSelectDao;
import com.springboot.dao.TeacherDao;
import com.springboot.domain.CourseCreate;
import com.springboot.domain.CourseRelease;
import com.springboot.domain.CourseSelect;
import com.springboot.domain.Teacher;
import com.springboot.enums.*;
import com.springboot.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Autowired
    CourseCreateDao courseCreateDao;

    @Autowired
    CourseReleaseDao courseReleaseDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    CourseSelectDao courseSelectDao;


    @Override
    public String createCourse(int teacherId, String name, String description) {
        Teacher teacher = teacherDao.findById(teacherId);
        CourseCreate courseCreate = new CourseCreate(name, description, ApproveState.WAITING, teacher);
        courseCreateDao.addCourseCreate(courseCreate);
        return "success";
    }

    @Override
    public List<CourseCreate> getCreateAndPassCourses(int teacherId) {
        return courseCreateDao.getCreateAndPassCourse(teacherId,ApproveState.PASSED);
    }

    @Override
    public String releaseCourse(int courseCreateId, GradeType gradeType, int limitNum, Term term) {
        CourseCreate courseCreate = courseCreateDao.findById(courseCreateId);
        Teacher teacher = courseCreate.getTeacher();
        CourseRelease courseRelease = new CourseRelease(gradeType,limitNum, ApproveState.WAITING, term, teacher, courseCreate);
        courseReleaseDao.addCourseRelease(courseRelease);
        return "success";
    }

    @Override
    public String beginClass(int courseReleaseId) {
        CourseRelease courseRelease = courseReleaseDao.findById(courseReleaseId);
        courseRelease.setCourseState(CourseState.REELECT);
        courseReleaseDao.modifyCourseRelease(courseRelease);

        int limitNum = courseRelease.getLimitNum();


        List<CourseSelect> courseSelectList = courseSelectDao.findAllCSByCRIdAndState(courseReleaseId, SelectState.SELECTED);
        if (courseSelectList.size() <= limitNum) {
            for (int i = 0; i < courseSelectList.size(); i++) {
                courseSelectList.get(i).setState(SelectState.ONGOING);
                courseSelectDao.modifyCourseSelect(courseSelectList.get(i));
            }
        } else {
            Set<Integer> randomSet = new HashSet<>();
            Random random = new Random();

            while (randomSet.size() < limitNum) {
                int i = random.nextInt(courseSelectList.size());
//                courseSelectList.get(i)
                randomSet.add(i);
            }
            for (Integer index : randomSet) {
                courseSelectList.get(index).setState(SelectState.ONGOING);
                courseSelectDao.modifyCourseSelect(courseSelectList.get(index));
            }
            List<CourseSelect> coursesSelectFails = courseSelectDao.findAllCSByCRIdAndState(courseReleaseId, SelectState.SELECTED);
            for (CourseSelect courseSelect : coursesSelectFails) {
                courseSelect.setState(SelectState.FAILED);
                courseSelectDao.modifyCourseSelect(courseSelect);
            }


        }

        return "success";
    }

    @Override
    public String officialBeginClass(int courseReleaseId) {


        CourseRelease courseRelease = courseReleaseDao.findById(courseReleaseId);
        if (!courseRelease.getCourseState().equals(CourseState.REELECT)) {
            return "还没补选无法直接开课";
        }

        courseRelease.setCourseState(CourseState.BEGIN);
        courseReleaseDao.modifyCourseRelease(courseRelease);
        return "success";
    }


}
