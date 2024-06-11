package com.moais.todo.common.web;

import lombok.Getter;

@Getter
public abstract class Response<T> {
    private final String status;
    private final String message;
    private final T data;

    public Response(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
