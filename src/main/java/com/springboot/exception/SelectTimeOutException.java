package com.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "通选时间已过")
public class SelectTimeOutException extends RuntimeException{
}
