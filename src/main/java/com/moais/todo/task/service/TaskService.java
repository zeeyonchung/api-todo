package com.moais.todo.task.service;

import com.moais.todo.task.domain.Task;
import com.moais.todo.task.repo.TaskRepository;
import lombok.RequiredArgsConstructor;
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
}
