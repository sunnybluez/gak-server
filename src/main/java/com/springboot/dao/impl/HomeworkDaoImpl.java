package com.springboot.dao.impl;

import com.springboot.dao.HomeworkDao;
import com.springboot.dao.impl.jpaRepository.HomeworkRepository;
import com.springboot.domain.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HomeworkDaoImpl implements HomeworkDao {

    @Autowired
    HomeworkRepository homeworkRepository;


    @Override
    public void addHomeWork(Homework homework) {
        homeworkRepository.save(homework);
    }

    @Override
    public List<Homework> findAllHomeWorkByCourseReleaseId(int courseReleaseId) {
        return homeworkRepository.findAllByCourseReleaseId(courseReleaseId);

    }

    @Override
    public Homework findById(int homeworkId) {
        return homeworkRepository.findById(homeworkId);
    }

}
