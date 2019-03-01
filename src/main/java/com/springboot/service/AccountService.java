package com.springboot.service;

import com.springboot.enums.UserIdentity;

public interface AccountService {

    String login(String email, String password, UserIdentity identity);

    String register(String email, String password, UserIdentity identity);

    String authenticate(String token);

    String cancelStudentAccount(int id);

}
