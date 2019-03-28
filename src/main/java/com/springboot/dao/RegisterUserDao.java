package com.springboot.dao;

import com.springboot.domain.RegisterUser;
import com.springboot.enums.UserIdentity;

import java.util.List;

public interface RegisterUserDao {

    RegisterUser findByEmail(String email);

    void addRegisterUser(RegisterUser registerUser);

    void modifyRegisterUser(RegisterUser registerUser);

    List<RegisterUser> findAllByUserIdentityAndAuthenticated(UserIdentity userIdentity, boolean authenticated);

}
