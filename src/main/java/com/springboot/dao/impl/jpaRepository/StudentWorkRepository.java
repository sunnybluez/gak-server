package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.StudentWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentWorkRepository extends JpaRepository<StudentWork, Integer> {

    List<StudentWork> findAllByCourseSelectId(int courseSelectId);

    StudentWork findByCourseSelectIdAndHomeworkId(int courseSelectId, int homeworkId);

    List<StudentWork> findAllByHomeworkId(int homeworkId);
}
