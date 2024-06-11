package com.moais.todo.common.web;

import lombok.Getter;

@Getter
public class EmptyResponse extends SuccessResponse<EmptyResponse.Data> {
    private static final EmptyResponse INSTANCE = new EmptyResponse();

    private EmptyResponse() {
        super(new Data());
    }

    public static EmptyResponse getInstance() {
        return INSTANCE;
    }

    record Data() {}
}
