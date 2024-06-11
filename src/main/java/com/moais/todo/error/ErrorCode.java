package com.moais.todo.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    BAD_REQUEST("B1001", "Bad request.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("M1001", "An exception occurred.", HttpStatus.INTERNAL_SERVER_ERROR),
    JOIN_EXISTING_MEMBER("J1001", "The loginId already exists.", HttpStatus.BAD_REQUEST),
    LOGIN_WRONG_ARGUMENT("L1001", "Wrong loginId or wrong password.", HttpStatus.BAD_REQUEST),
    LOGIN_UNAUTHORIZED("L1002", "Unauthorized.", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
