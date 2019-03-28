package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.CourseCreate;
import com.springboot.enums.ApproveState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseCreateRepository extends JpaRepository<CourseCreate, Integer> {
    CourseCreate findById(int id);

    List<CourseCreate> findAllByTeacherIdAndState(int teacherId, ApproveState state);

    List<CourseCreate> findAllByState(ApproveState state);

}
