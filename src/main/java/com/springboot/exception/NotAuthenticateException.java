package com.springboot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "账户未验证或注销，无法使用!请前往邮箱验证")
public class NotAuthenticateException extends RuntimeException {

}
