package com.moais.todo.task.service.dto;

import com.moais.todo.task.domain.Task;
import com.moais.todo.task.domain.TaskStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskRes {
    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final TaskStatus status;

    public TaskRes(Task task) {
        this.id = task.getId();
        this.content = task.getContent();
        this.createdAt = task.getCreatedAt();
        this.modifiedAt = task.getModifiedAt();
        this.status = task.getStatus();
    }
}
