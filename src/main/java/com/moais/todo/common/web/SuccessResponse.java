package com.moais.todo.common.web;

import lombok.Getter;

@Getter
public abstract class SuccessResponse<T> extends Response<T> {
    private static final String STATUS = "success";
    private static final String MESSAGE = "The request successfully has been processed.";

    public SuccessResponse(T data) {
        super(STATUS, MESSAGE, data);
    }
}
