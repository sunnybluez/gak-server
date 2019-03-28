package com.springboot.service;

import com.springboot.enums.UserIdentity;

import java.util.HashMap;
import java.util.List;

public interface AccountService {

    HashMap<String, Object> login(String email, String password, UserIdentity identity);

    String register(String email, String password, UserIdentity identity);

    String authenticate(String token);

    String cancelStudentAccount(int id);

    List<Integer> getRecentSevenLoginNum(UserIdentity userIdentity);


}
