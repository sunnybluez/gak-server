package com.springboot.service;

import com.springboot.domain.Manager;

public interface ManagerService {

    Manager findByEmail(String email);
}
