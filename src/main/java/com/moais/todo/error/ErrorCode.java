package com.moais.todo.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("M1001", "An exception occurred.", HttpStatus.INTERNAL_SERVER_ERROR),
    JOIN_EXISTING_MEMBER("J1001", "The memberId already exists.", HttpStatus.BAD_REQUEST),
    LOGIN_WRONG_ARGUMENT("L1001", "Wrong memberId or wrong password.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
