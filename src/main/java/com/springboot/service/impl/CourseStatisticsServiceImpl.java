package com.springboot.service.impl;

import com.springboot.dao.CourseReleaseDao;
import com.springboot.dao.CourseSelectDao;
import com.springboot.domain.CourseRelease;
import com.springboot.domain.CourseSelect;
import com.springboot.enums.SelectState;
import com.springboot.service.CourseStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseStatisticsServiceImpl implements CourseStatisticsService{

    @Autowired
    CourseSelectDao courseSelectDao;

    @Autowired
    CourseReleaseDao courseReleaseDao;

    @Override
    public int getSelectNum(int courseReleaseId) {

        CourseRelease courseRelease = courseReleaseDao.findById(courseReleaseId);

        List<CourseSelect> courseSelectList = courseSelectDao.findAllByCRId(courseRelease.getId());


        int count = 0;
        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getState().equals(SelectState.SELECTED)) {
                count++;
            }
        }
        return count;

    }
}
