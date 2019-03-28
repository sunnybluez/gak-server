package com.springboot.dao;

import com.springboot.domain.Manager;

public interface ManagerDao {
    Manager findByEmail(String email);

    void addManager(Manager manager);

}
