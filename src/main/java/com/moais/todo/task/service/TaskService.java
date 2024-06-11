package com.moais.todo.task.service;

import com.moais.todo.task.domain.Task;
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
}
