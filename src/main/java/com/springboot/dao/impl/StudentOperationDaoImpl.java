package com.springboot.dao.impl;

import com.springboot.dao.StudentOperationDao;
import com.springboot.dao.impl.jpaRepository.StudentOperationRepository;
import com.springboot.domain.StudentOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentOperationDaoImpl implements StudentOperationDao {

    @Autowired
    StudentOperationRepository studentOperationRepository;

    @Override
    public void addStudentOperation(StudentOperation studentOperation) {
        studentOperationRepository.save(studentOperation);
    }

    @Override
    public List<StudentOperation> getStudentOperation(int studentId) {
        return studentOperationRepository.findAllByStudentId(studentId);
    }
}
