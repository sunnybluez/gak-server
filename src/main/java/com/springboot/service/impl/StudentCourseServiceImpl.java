package com.springboot.service.impl;

import com.springboot.dao.CourseReleaseDao;
import com.springboot.dao.CourseSelectDao;
import com.springboot.dao.StudentDao;
import com.springboot.dao.StudentOperationDao;
import com.springboot.domain.CourseRelease;
import com.springboot.domain.CourseSelect;
import com.springboot.domain.Student;
import com.springboot.domain.StudentOperation;
import com.springboot.enums.*;
import com.springboot.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    CourseReleaseDao courseReleaseDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseSelectDao courseSelectDao;

    @Autowired
    StudentOperationDao studentOperationDao;


    @Override
    public List<CourseRelease> getAllPassRCourseByTerm(Term term) {
        return courseReleaseDao.findAllCRByTermAndState(term, ApproveState.PASSED);
    }

    @Override
    public String selectCourse(int studentId, int courseReleaseId) {
        // 检查选课时间是否有效
        CourseRelease courseRelease = courseReleaseDao.findById(courseReleaseId);
        if (!courseRelease.getCourseState().equals(CourseState.FUCK)) {
            return "初选时间已过";
        }


        //检查是否选过
        Student student = studentDao.findById(studentId);
        List<CourseSelect> courseSelectList = student.getCourseSelectList();

        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getCourseRelease().getId() == courseReleaseId
//                    && (courseSelect.getState().equals(SelectState.ONGOING) ||
//                        courseSelect.getState().equals(SelectState.SELECTED))
                    ) {
                if(courseSelect.getState().equals(SelectState.RETURNED)){
                    courseSelect.setState(SelectState.SELECTED);
                    courseSelectDao.modifyCourseSelect(courseSelect);
                    logStudentOperation(studentId, courseReleaseId, OperateType.SELECT);
                    return "选课成功";
                }else if(courseSelect.getState().equals(SelectState.SELECTED)){
                    return "已选过该课";
                }

            }
        }

        //成功选课
        CourseSelect courseSelect = new CourseSelect(SelectState.SELECTED, courseRelease, student);
        courseSelectDao.addCourseSelect(courseSelect);
        //日志记录
        logStudentOperation(studentId, courseReleaseId, OperateType.SELECT);
        return "选课成功";

    }

    @Override  //默认只有select或者ongoing才会去退课 没做检查
    public String dropCourse(int courseSelectId) {
        CourseSelect courseSelect = courseSelectDao.findById(courseSelectId);
        if (courseSelect.getCourseRelease().getCourseState().equals(CourseState.BEGIN)) {
            return "已经开课无法退课";
        }

        courseSelect.setState(SelectState.RETURNED);
        courseSelectDao.modifyCourseSelect(courseSelect);
        logStudentOperation(courseSelect.getStudent().getId(), courseSelect.getCourseRelease().getId(), OperateType.DROP);
        return "退选成功";
    }

    @Override
    public String reelectCourse(int studentId, int courseReleaseId) {
        //检查选课时间是否有效
        CourseRelease courseRelease = courseReleaseDao.findById(courseReleaseId);
        if (courseRelease.getState().equals(CourseState.BEGIN)) {
            return "补选时间已过";
        }

        //检查选课人数是否已满
        List<CourseSelect> allCSinCRId = courseSelectDao.findAllCSByCRIdAndState(courseReleaseId, SelectState.ONGOING);
        int count = allCSinCRId.size();
        if (count < courseRelease.getLimitNum()) {
            System.out.println( "已选" + count + "人");
        }else {
            return "选课人数已满";
        }

//        CourseSelect courseSelect = courseSelectDao.
        //检查是否属于曾经的落选或者退课人员
        Student student = studentDao.findById(studentId);
        List<CourseSelect> courseSelectList = student.getCourseSelectList();

        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getCourseRelease().getId() == courseReleaseId) {
                if(courseSelect.getState().equals(SelectState.RETURNED) ||
                        courseSelect.getState().equals(SelectState.FAILED)){

                    courseSelect.setState(SelectState.ONGOING);
                    courseSelectDao.modifyCourseSelect(courseSelect);
                    logStudentOperation(studentId, courseReleaseId, OperateType.SELECT);
                    return "补选成功";
                } else if (courseSelect.getState().equals(SelectState.ONGOING)) {
                    return "正在上课";
                }
                return "出错 补选阶段出现初选";
            }

        }

        //补选中属于第一次选该课的
        CourseSelect courseSelect = new CourseSelect(SelectState.ONGOING, courseRelease, student);
        courseSelectDao.addCourseSelect(courseSelect);
        //日志记录
        logStudentOperation(studentId, courseReleaseId, OperateType.SELECT);
        return "补选成功";

    }

    @Override
    public List<CourseRelease> getMyOnCoursesByTerm(int studentId, Term term) {
        List<CourseSelect> courseSelectList = courseSelectDao.findAllCSBySidAndState(studentId, SelectState.ONGOING);
        List<CourseRelease> courseReleaseList = new ArrayList<>();

        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getCourseRelease().getTerm().equals(term)) {
                courseReleaseList.add(courseSelect.getCourseRelease());
            }
        }
        return courseReleaseList;

    }

    /**
     * 有课程状态的课程肯定都是通过审批的所以这里就不加审批pass条件了
     * @param studentId
     * @param term
     * @return
     */
    @Override
    public List<CourseRelease> getAllCanSelectCourseByTerm(int studentId, Term term) {
//        System.out.println(1);
        long start = System.currentTimeMillis();
        List<CourseRelease> courseReleaseList = courseReleaseDao.findAllCRByTermAndCourseState(term, CourseState.FUCK);
//        System.out.println(2);
        long end = System.currentTimeMillis();
        System.out.println((end - start)+"ms");

        List<Integer> releaseIdList = new ArrayList<>();
        List<CourseSelect> courseSelectList = studentDao.findById(studentId).getCourseSelectList();
        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getState().equals(SelectState.SELECTED)) {
                releaseIdList.add(courseSelect.getCourseRelease().getId());
            }
        }
//        System.out.println(3);
        List<CourseRelease> resultList = new ArrayList<>();
        for (CourseRelease courseRelease : courseReleaseList) {
            if (!releaseIdList.contains(courseRelease.getId())) {
                resultList.add(courseRelease);
            }
        }
//        System.out.println(4);
        return resultList;

    }

    public void logStudentOperation(int studentId, int courseReleaseId, OperateType operateType) {
        StudentOperation studentOperation = new StudentOperation(studentId, courseReleaseId, operateType);
        studentOperationDao.addStudentOperation(studentOperation);
    }


}
