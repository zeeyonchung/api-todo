package com.moais.todo.common.web;

import lombok.Getter;

@Getter
public class ErrorResponse extends Response<ErrorResponse.Data> {
    private static final String STATUS = "error";

    public ErrorResponse(String message, String code) {
        super(STATUS, message, new Data(code));
    }

    record Data(String code) {}
}
