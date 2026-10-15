package com.angelly12.taskmanager.core.infrastructure.web.controller;

import com.angelly12.taskmanager.core.application.mapper.TaskMapper;
import com.angelly12.taskmanager.core.application.service.TaskService;
import com.angelly12.taskmanager.core.domain.entity.Task;
import com.angelly12.taskmanager.core.infrastructure.web.dto.request.CreateTaskRequest;
import com.angelly12.taskmanager.core.infrastructure.web.dto.request.UpdateTaskRequest;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.ApiResponses;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.ResponseSuccess;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.TaskSummaryResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService service;
    public TaskController(TaskService service)
    {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseSuccess<Void>> create(
            @Valid @RequestBody CreateTaskRequest request
            ){
        Task task = TaskMapper.createToDomain(request);
        Task created = service.create(task);
        return ApiResponses.created("Task "+created.getTitle()+ " created successfully");
    }

    @GetMapping
    public ResponseEntity<ResponseSuccess<List<TaskSummaryResponse>>> list()
    {
        return ApiResponses.success("List of tasks found", service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Task>> findById(
            @PathVariable Long id
    )
    {
        return ApiResponses.success("Task found", service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Void>> update(
            @Valid @RequestBody UpdateTaskRequest request,
            @PathVariable Long id
            )
    {
        Task task = TaskMapper.updateToDomain(request);
        Task updated = service.update(id, task);
        return ApiResponses.success("Task "+updated.getTitle()+ " updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    )
    {
        service.delete(id);
        return ApiResponses.noContent();
    }
}
