package com.springboot.dao;

import com.springboot.domain.Homework;

import java.util.List;

public interface HomeworkDao {

    void addHomeWork(Homework homework);

    List<Homework> findAllHomeWorkByCourseReleaseId(int courseReleaseId);

    Homework findById(int homeworkId);
}
