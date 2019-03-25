package com.springboot.dao;

import com.springboot.domain.CourseSelect;
import com.springboot.enums.SelectState;

import java.util.List;

public interface CourseSelectDao {

    void addCourseSelect(CourseSelect courseSelect);

//    List<CourseSelect> findCSBySidAndCRid(int studentId,int )

    List<CourseSelect> findAllCSByCRIdAndState(int courseReleaseId, SelectState selectState);

    void modifyCourseSelect(CourseSelect courseSelect);

    CourseSelect findById(int courseSelectedId);

    List<CourseSelect> findAllCSBySidAndState(int studentId, SelectState state);

    List<CourseSelect> findAllByCRId(int courseReleaseId);

    List<CourseSelect> findAllByStudentId(int studentId);

    CourseSelect findByStudentIdAndCourseReleaseId(int studentId, int courseReleaseId);


}
