package com.springboot.service.impl;

import com.springboot.dao.CourseReleaseDao;
import com.springboot.dao.CourseSelectDao;
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


        List<CourseSelect> courseSelectList = courseSelectDao.findAllByCRId(courseReleaseId);


        int count = 0;
        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getState().equals(SelectState.SELECTED)) {
                count++;
            }
        }
        return count;

    }

    @Override
    public int getOngoingNum(int courseReleaseId) {
        System.out.println("crid" + courseReleaseId);
        List<CourseSelect> courseSelectList = courseSelectDao.findAllByCRId(courseReleaseId);
        System.out.println("list size" + courseSelectList.size());

        int count = 0;
        for (CourseSelect courseSelect : courseSelectList) {
            if (courseSelect.getState().equals(SelectState.ONGOING)) {
                count++;
            }
        }
        return count;

    }
}
