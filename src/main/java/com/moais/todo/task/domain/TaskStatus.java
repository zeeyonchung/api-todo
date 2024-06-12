package com.moais.todo.task.domain;

public enum TaskStatus {
    TODO ("할 일") {
        @Override
        boolean canChangeTo(TaskStatus toStatus) {
            return toStatus != PENDING;
        }
    },
    IN_PROGRESS ("진행 중") {
        @Override
        boolean canChangeTo(TaskStatus toStatus) {
            return true;
        }
    },
    DONE ("완료") {
        @Override
        boolean canChangeTo(TaskStatus toStatus) {
            return toStatus != PENDING;
        }
    },
    PENDING ("대기") {
        @Override
        boolean canChangeTo(TaskStatus toStatus) {
            return true;
        }
    };

    private final String description;
    abstract boolean canChangeTo(TaskStatus toStatus);

    TaskStatus(String description) {
        this.description = description;
    }
}
