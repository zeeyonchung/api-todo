package com.moais.todo.common.web;

import lombok.Getter;

@Getter
public class ErrorResponse extends Response<ErrorResponse.Data> {
    private static final String STATUS = "error";

    public ErrorResponse(Exception e, String code) {
        super(STATUS, e.getLocalizedMessage(), new Data(code));
    }

    record Data(String code) {}
}
