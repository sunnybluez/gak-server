package com.springboot.dao;


import com.springboot.domain.StudentOperation;

import java.util.List;

public interface StudentOperationDao {

    void addStudentOperation(StudentOperation studentOperation);

    List<StudentOperation> getStudentOperation(int studentId);

}
