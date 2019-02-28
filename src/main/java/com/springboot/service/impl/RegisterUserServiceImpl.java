package com.springboot.service.impl;

import com.springboot.dao.RegisterUserDao;
import com.springboot.domain.RegisterUser;
import com.springboot.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private RegisterUserDao registerUserDao;


    @Override
    public RegisterUser findByEmail(String email) {
        return registerUserDao.findByEmail(email);
    }

    @Override
    public void addRegisterUser(RegisterUser registerUser) {
        registerUserDao.addRegisterUser(registerUser);
    }

    @Override
    public void modifyRegisterUser(RegisterUser registerUser) {
        registerUserDao.modifyRegisterUser(registerUser);
    }


}
