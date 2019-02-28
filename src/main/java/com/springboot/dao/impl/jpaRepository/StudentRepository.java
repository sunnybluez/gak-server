package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByEmail(String email);

    Student findById(int id);

//    @Query
//    @Modifying


}
