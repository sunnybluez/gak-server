package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.StudentOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentOperationRepository extends JpaRepository<StudentOperation, Integer> {

    List<StudentOperation> findAllByStudentId(int studentId);

}
