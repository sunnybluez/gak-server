package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Integer> {

    List<Homework> findAllByCourseReleaseId(int courseReleaseId);

    Homework findById(int homeWorkId);
}
