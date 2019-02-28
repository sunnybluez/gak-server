package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.CourseCreate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCreateRepository extends JpaRepository<CourseCreate, Integer> {
}
