package com.moais.todo.task.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moais.todo.task.service.dto.TaskListRes;
import com.moais.todo.task.service.dto.TaskRes;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetTaskListRes {
    private final List<GetTaskRes> tasks;
    private final long totalElements;

    public GetTaskListRes(TaskListRes tasks) {
        this.tasks = tasks.getTasks().stream()
                .map(GetTaskRes::new).collect(Collectors.toList());
        this.totalElements = tasks.getTotalElements();
    }

    @Getter
    private static class GetTaskRes {
        private final Long id;
        private final String content;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private final LocalDateTime createdAt;

        private GetTaskRes(TaskRes task) {
            this.id = task.getId();
            this.content = task.getContent();
            this.createdAt = task.getCreatedAt();
        }
    }
}
