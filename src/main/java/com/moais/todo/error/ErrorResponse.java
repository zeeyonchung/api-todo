package com.moais.todo.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private static final String DEFAULT_MESSAGE = "An exception occurred.";

    private final int code;
    private final String message;

    private ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(Exception e, HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus.value(), e.getLocalizedMessage());
    }
}
