package com.moais.todo.task.service.dto;

import com.moais.todo.task.domain.Task;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TaskListRes {
    private final List<TaskRes> tasks;
    private final long totalElements;

    public TaskListRes(Page<Task> tasks) {
        this.tasks = tasks.stream().map(TaskRes::new)
                .collect(Collectors.toList());
        this.totalElements = tasks.getTotalElements();
    }
}
