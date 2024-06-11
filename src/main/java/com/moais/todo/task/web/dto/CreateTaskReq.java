package com.moais.todo.task.web.dto;

import com.moais.todo.task.domain.Task;
import lombok.Getter;

@Getter
public class CreateTaskReq {
    private String content;

    public Task toTask(Long memberId) {
        return new Task(content, memberId);
    }
}
