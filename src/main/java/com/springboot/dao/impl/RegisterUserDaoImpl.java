package com.springboot.dao.impl;

import com.springboot.dao.RegisterUserDao;
import com.springboot.dao.impl.jpaRepository.RegisterUserRepository;
import com.springboot.domain.RegisterUser;
import com.springboot.enums.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisterUserDaoImpl implements RegisterUserDao {

    @Autowired
    private RegisterUserRepository registerUserRepository;

    @Override
    public RegisterUser findByEmail(String email) {
        return registerUserRepository.findByEmail(email);
    }

    @Override
    public void addRegisterUser(RegisterUser registerUser) {
        registerUserRepository.save(registerUser);
    }

    @Override
    public void modifyRegisterUser(RegisterUser registerUser) {
        registerUserRepository.save(registerUser);
    }

    @Override
    public List<RegisterUser> findAllByUserIdentityAndAuthenticated(UserIdentity userIdentity, boolean authenticated) {
        return registerUserRepository.findAllByAuthenticatedAndAndUserIdentity(authenticated, userIdentity);
    }

}
