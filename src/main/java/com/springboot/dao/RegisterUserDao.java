package com.springboot.dao;

import com.springboot.domain.RegisterUser;

public interface RegisterUserDao {

    RegisterUser findByEmail(String email);

    void addRegisterUser(RegisterUser registerUser);

    void modifyRegisterUser(RegisterUser registerUser);
}
