package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.StudentWork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentWorkRepository extends JpaRepository<StudentWork, Integer> {
}
