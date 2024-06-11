package com.moais.todo.task.domain;

public enum TaskStatus {
    TODO ("할 일"),
    IN_PROGRESS ("진행 중"),
    DONE ("완료"),
    PENDING ("대기");

    private final String description;

    private TaskStatus(String description) {
        this.description = description;
    }
}
