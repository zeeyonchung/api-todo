package com.moais.todo.task.web.dto;

import com.moais.todo.task.domain.TaskStatus;
import lombok.Getter;

@Getter
public class UpdateTaskReq {
    private TaskStatus toStatus;
}
