package com.moais.todo.member.web.dto;

import com.moais.todo.common.web.SuccessResponse;
import lombok.Getter;

@Getter
public class LoginRes extends SuccessResponse<LoginRes.Data> {
    public LoginRes(Long id) {
        super(new Data(id));
    }

    record Data(Long id) {}
}

