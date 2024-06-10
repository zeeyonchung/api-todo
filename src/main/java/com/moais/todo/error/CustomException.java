package com.moais.todo.error;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode, String hint) {
        super(String.format("%s : %s", errorCode.getMessage(), hint));
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, Throwable cause, String hint) {
        super(String.format("%s : %s", errorCode.getMessage(), hint), cause);
        this.errorCode = errorCode;
    }
}
