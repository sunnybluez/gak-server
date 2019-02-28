package com.springboot.service;

import com.springboot.domain.RegisterUser;

public interface RegisterUserService {

    RegisterUser findByEmail(String email);

    void addRegisterUser(RegisterUser registerUser);

    void modifyRegisterUser(RegisterUser registerUser);
}
