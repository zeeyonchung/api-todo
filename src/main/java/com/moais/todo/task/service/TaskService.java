package com.moais.todo.task.service;

import com.moais.todo.common.error.CustomException;
import com.moais.todo.common.error.ErrorCode;
import com.moais.todo.task.domain.Task;
import com.moais.todo.task.domain.TaskStatus;
import com.moais.todo.task.repo.TaskRepository;
import com.moais.todo.task.service.dto.TaskListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public void add(Task task) {
        taskRepository.save(task);
    }

    public TaskListRes find(Long memberId, Pageable pageable) {
        Page<Task> tasks = taskRepository.findByMemberId(memberId, pageable);
        return new TaskListRes(tasks);
    }

    @Transactional
    public void changeStatus(Long taskId, Long memberId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST, taskId.toString()));

        if (!task.getMemberId().equals(memberId)) {
            throw new CustomException(ErrorCode.LOGIN_FORBIDDEN, memberId.toString());
        }

        task.changeStatusTo(status);
    }
}
