package com.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "选了还选，重复选择无意义")
public class DuplicateSelectException extends RuntimeException {
}
