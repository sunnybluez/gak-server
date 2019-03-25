package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.CourseSelect;
import com.springboot.enums.SelectState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSelectRepository extends JpaRepository<CourseSelect, Integer> {

    List<CourseSelect> findAllByCourseReleaseIdAndState(int courseReleaseId, SelectState state);

    CourseSelect findById(int id);

    List<CourseSelect> findAllByStudentIdAndState(int studentId, SelectState state);

    List<CourseSelect> findAllByCourseReleaseId(int courseReleaseId);

    List<CourseSelect> findAllByStudentId(int studentId);

    CourseSelect findByStudentIdAndCourseReleaseId(int studentId, int courseReleaseId);


}
