package com.springboot.service.impl;

import com.springboot.dao.ManagerDao;
import com.springboot.domain.Manager;
import com.springboot.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Override
    public Manager findByEmail(String email) {
        return managerDao.findByEmail(email);
    }
}
