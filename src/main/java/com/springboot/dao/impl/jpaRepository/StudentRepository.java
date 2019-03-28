package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.Student;
import com.springboot.enums.GradeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByEmail(String email);

    Student findById(int id);

    List<Student> findAllByGrade(GradeType gradeType);

//    @Query
//    @Modifying


}
