package com.springboot.dao.impl;

import com.springboot.dao.ManagerDao;
import com.springboot.dao.impl.jpaRepository.ManagerRepository;
import com.springboot.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Manager findByEmail(String email) {
        return managerRepository.findByEmail(email);
    }

    @Override
    public void addManager(Manager manager) {
        managerRepository.save(manager);
    }

}
