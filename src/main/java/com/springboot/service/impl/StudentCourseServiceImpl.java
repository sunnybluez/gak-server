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
import com.springboot.exception.DuplicateSelectException;
import com.springboot.exception.NoQuotaException;
import com.springboot.exception.ReelectTimeOutException;
import com.springboot.exception.SelectTimeOutException;
import com.springboot.service.CourseStatisticsService;
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

    @Autowired
    CourseStatisticsService courseStatisticsService;


    @Override
    public List<CourseRelease> getAllPassRCourseByTerm(Term term) {
        return courseReleaseDao.findAllCRByTermAndState(term, ApproveState.PASSED);
    }

    @Override
    public String selectCourse(int studentId, int courseReleaseId) {
        // 检查选课时间是否有效
        CourseRelease courseRelease = courseReleaseDao.findById(courseReleaseId);
        if (!courseRelease.getCourseState().equals(CourseState.GENERAL)) {
            throw new SelectTimeOutException();
        }


        //检查是否选过
        Student student = studentDao.findById(studentId);
        List<CourseSelect> courseSelectList = courseSelectDao.findAllByStudentId(student.getId());

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
                    throw new DuplicateSelectException();
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
    public String dropCourse(int studentId,int courseReleaseId) {

        CourseSelect courseSelect = courseSelectDao.findByStudentIdAndCourseReleaseId(studentId, courseReleaseId);
        if (courseSelect.getCourseRelease().getCourseState().equals(CourseState.BEGIN)) {
            throw new ReelectTimeOutException();
        }

        courseSelect.setState(SelectState.RETURNED);
        courseSelectDao.modifyCourseSelect(courseSelect);
        logStudentOperation(courseSelect.getStudent().getId(), courseSelect.getCourseRelease().getId(), OperateType.DROP);
        return "退课成功";
    }

    @Override
    public String reelectCourse(int studentId, int courseReleaseId) {
        //检查选课时间是否有效
        CourseRelease courseRelease = courseReleaseDao.findById(courseReleaseId);
        if (courseRelease.getState().equals(CourseState.BEGIN)) {
            throw new ReelectTimeOutException();
        }

        //检查选课人数是否已满
//        List<CourseSelect> allCSinCRId = courseSelectDao.findAllCSByCRIdAndState(courseReleaseId, SelectState.ONGOING);
//        int count = allCSinCRId.size();
//        if (count < courseRelease.getLimitNum()) {
//            System.out.println( "已选" + count + "人");
//        }else {
//            return "选课人数已满";
//        }
        int count = courseStatisticsService.getOngoingNum(courseReleaseId);
        if (count < courseRelease.getLimitNum()) {
            System.out.println( "已选" + count + "人");
        }else {
            throw new NoQuotaException();
        }

//        CourseSelect courseSelect = courseSelectDao.
        //检查是否属于曾经的落选或者退课人员
        Student student = studentDao.findById(studentId);
        List<CourseSelect> courseSelectList = courseSelectDao.findAllByStudentId(student.getId());

        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getCourseRelease().getId() == courseReleaseId) {
                if(courseSelect.getState().equals(SelectState.RETURNED) ||
                        courseSelect.getState().equals(SelectState.FAILED)){

                    courseSelect.setState(SelectState.ONGOING);
                    courseSelectDao.modifyCourseSelect(courseSelect);
                    logStudentOperation(studentId, courseReleaseId, OperateType.REELECT);
                    return "补选成功";
                } else if (courseSelect.getState().equals(SelectState.ONGOING)) {
                    throw new DuplicateSelectException();
                }
                throw new SelectTimeOutException();
            }

        }

        //补选中属于第一次选该课的
        CourseSelect courseSelect = new CourseSelect(SelectState.ONGOING, courseRelease, student);
        courseSelectDao.addCourseSelect(courseSelect);
        //日志记录
        logStudentOperation(studentId, courseReleaseId, OperateType.REELECT);
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

        List<CourseRelease> courseReleaseList = courseReleaseDao.findAllCRByTermAndCourseState(term, CourseState.GENERAL);

        List<Integer> releaseIdList = new ArrayList<>();
        Student student = studentDao.findById(studentId);
        List<CourseSelect> courseSelectList = courseSelectDao.findAllByStudentId(student.getId());

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

    @Override
    public List<CourseRelease> getAllCanReelectCourseByTerm(int studentId, Term term) {
        List<CourseRelease> courseReleaseList = courseReleaseDao.findAllCRByTermAndCourseState(term, CourseState.REELECT);

        List<Integer> releaseIdList = new ArrayList<>();
        Student student = studentDao.findById(studentId);
        List<CourseSelect> courseSelectList = courseSelectDao.findAllByStudentId(student.getId());

        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getState().equals(SelectState.ONGOING)) {
                releaseIdList.add(courseSelect.getCourseRelease().getId());
            }
        }
        List<CourseRelease> resultList = new ArrayList<>();
        for (CourseRelease courseRelease : courseReleaseList) {
            if (!releaseIdList.contains(courseRelease.getId())) {
                resultList.add(courseRelease);
            }
        }
//        System.out.println(4);
        return resultList;
    }

    @Override
    public List<CourseRelease> getAllSelectedCourseByTerm(int studentId, Term term) {

        List<CourseSelect> courseSelectList = courseSelectDao.findAllByStudentId(studentId);

        List<CourseRelease> courseReleaseResults = new ArrayList<>();
        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getState().equals(SelectState.SELECTED) && courseSelect.getCourseRelease().getTerm().equals(term)) {
                courseReleaseResults.add(courseSelect.getCourseRelease());
            }

        }
        return courseReleaseResults;

    }

    @Override
    public List<CourseRelease> getAllFailedCourseByTerm(int studentId, Term term) {
        List<CourseSelect> courseSelectList = courseSelectDao.findAllByStudentId(studentId);

        List<CourseRelease> courseReleaseResults = new ArrayList<>();
        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getState().equals(SelectState.FAILED) && courseSelect.getCourseRelease().getTerm().equals(term)) {
                courseReleaseResults.add(courseSelect.getCourseRelease());
            }

        }
        return courseReleaseResults;
    }

    @Override
    public List<CourseRelease> getAllOngoingCourseByTerm(int studentId, Term term) {
        List<CourseSelect> courseSelectList = courseSelectDao.findAllByStudentId(studentId);

        List<CourseRelease> courseReleaseResults = new ArrayList<>();
        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getState().equals(SelectState.ONGOING) && courseSelect.getCourseRelease().getTerm().equals(term)) {
                courseReleaseResults.add(courseSelect.getCourseRelease());
            }

        }
        return courseReleaseResults;
    }

    public void logStudentOperation(int studentId, int courseReleaseId, OperateType operateType) {
        StudentOperation studentOperation = new StudentOperation(studentId, courseReleaseId, operateType);
        studentOperationDao.addStudentOperation(studentOperation);
    }


}
