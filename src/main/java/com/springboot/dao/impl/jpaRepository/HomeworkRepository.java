package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
}
