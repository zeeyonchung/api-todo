package com.moais.todo.task.web;

import com.moais.todo.task.domain.Task;
import com.moais.todo.task.service.TaskService;
import com.moais.todo.task.web.dto.CreateTaskReq;
import com.moais.todo.task.web.dto.CreateTaskRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<CreateTaskRes> create(
            @RequestBody CreateTaskReq req,
            @SessionAttribute(name = "id") Long memberId) {
        Task task = req.toTask(memberId);
        taskService.add(task);
        return ResponseEntity.ok(new CreateTaskRes(task.getId()));
    }
}
