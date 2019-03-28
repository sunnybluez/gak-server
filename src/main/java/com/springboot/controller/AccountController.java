package com.springboot.controller;

import com.springboot.annotation.StudentAuth;
import com.springboot.enums.UserIdentity;
import com.springboot.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
//@CrossOrigin
@Slf4j
public class AccountController {


    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "test")
    public String test(){
        return "test(1111)";
    }

    @RequestMapping(value = "login")
    public HashMap<String, Object> login(String email, String password, String identity) {

        log.info("访问login");
//        System.out.println(identity);

        UserIdentity userIdentity = UserIdentity.valueOf(identity.toUpperCase());

        return accountService.login(email, password, userIdentity);

    }


    @RequestMapping(value = "register")
    public String register(String email, String password, String identity) {
        log.info("访问register");
        UserIdentity userIdentity = UserIdentity.valueOf(identity.toUpperCase());
        return accountService.register(email, password, userIdentity);

    }

    @RequestMapping(value = "authenticate")
    public String authenticate(String token) {
        return accountService.authenticate(token);

    }

    @RequestMapping(value = "cancelAccount")
    @StudentAuth
    public String cancelStudentAccount(int id) {
        return accountService.cancelStudentAccount(id);
    }


}
