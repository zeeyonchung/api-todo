package com.moais.todo.common.error;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private static final String DEFAULT_MESSAGE = "";

    private final String code;
    private final String message;

    private ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(Exception e, String code) {
        return new ErrorResponse(code, e.getLocalizedMessage());
    }
}
