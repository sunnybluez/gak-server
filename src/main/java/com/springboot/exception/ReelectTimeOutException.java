package com.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "补选时间已过 已经开课")
public class ReelectTimeOutException extends RuntimeException{
}
