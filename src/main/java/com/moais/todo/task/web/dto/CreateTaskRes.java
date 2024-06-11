package com.moais.todo.task.web.dto;

import com.moais.todo.common.web.SuccessResponse;
import lombok.Getter;

@Getter
public class CreateTaskRes extends SuccessResponse<CreateTaskRes.Data> {
    public CreateTaskRes(Long id) {
        super(new Data(id));
    }

    record Data(Long id) {}
}
