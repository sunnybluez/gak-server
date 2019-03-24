package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.StudentOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentOperationRepository extends JpaRepository<StudentOperation, Integer> {

}
