package com.moais.todo.member.web.dto;

import com.moais.todo.common.web.SuccessResponse;
import lombok.Getter;

@Getter
public class CreateMemberRes extends SuccessResponse<CreateMemberRes.Data> {
    public CreateMemberRes(Long id) {
        super(new Data(id));
    }

    record Data(Long id) {}
}
