package com.moais.todo.task.web;

import com.moais.todo.common.web.EmptyResponse;
import com.moais.todo.task.domain.Task;
import com.moais.todo.task.service.TaskService;
import com.moais.todo.task.service.dto.TaskListRes;
import com.moais.todo.task.web.dto.CreateTaskReq;
import com.moais.todo.task.web.dto.CreateTaskRes;
import com.moais.todo.task.web.dto.GetTaskListRes;
import com.moais.todo.task.web.dto.UpdateTaskReq;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/tasks")
    public ResponseEntity<GetTaskListRes> read(
            @SessionAttribute(name = "id") Long memberId,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        TaskListRes tasks = taskService.find(memberId, pageable);
        return ResponseEntity.ok(new GetTaskListRes(tasks));
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<EmptyResponse> update(
            @RequestBody UpdateTaskReq req,
            @PathVariable(name = "id") Long taskId,
            @SessionAttribute(name = "id") Long memberId) {
        taskService.changeStatus(taskId, memberId, req.getToStatus());
        return ResponseEntity.ok(EmptyResponse.getInstance());
    }
}
