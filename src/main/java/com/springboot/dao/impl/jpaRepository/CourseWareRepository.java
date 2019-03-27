package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.CourseWare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseWareRepository extends JpaRepository<CourseWare, Integer> {

    List<CourseWare> findAllByCourseCreateId(int courseCreateId);
}
