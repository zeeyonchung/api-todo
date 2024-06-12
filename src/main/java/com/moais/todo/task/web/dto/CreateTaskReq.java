package com.moais.todo.task.web.dto;

import com.moais.todo.task.domain.Task;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateTaskReq {
    @NotNull
    private String content;

    public Task toTask(Long memberId) {
        return new Task(content, memberId);
    }
}
