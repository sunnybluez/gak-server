package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByEmail(String email);

    Teacher findById(int id);
}
