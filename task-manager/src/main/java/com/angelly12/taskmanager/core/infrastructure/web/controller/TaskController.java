package com.angelly12.taskmanager.core.infrastructure.web.controller;

import com.angelly12.taskmanager.core.application.mapper.TaskMapper;
import com.angelly12.taskmanager.core.application.service.TaskService;
import com.angelly12.taskmanager.core.domain.entity.Task;
import com.angelly12.taskmanager.core.infrastructure.web.dto.request.CreateTaskRequest;
import com.angelly12.taskmanager.core.infrastructure.web.dto.request.UpdateTaskRequest;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.ApiResponses;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.ResponseError;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.ResponseSuccess;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.TaskSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Tasks", description = "Task management endpoints")
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService service;
    public TaskController(TaskService service)
    {
        this.service = service;
    }

    @Operation(
            summary = "Create task",
            description = "Returns a message with task title"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Task ${task_title} created successfully"
            ),

            @ApiResponse(
                    responseCode = "422",
                    description = "Request validation failed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )

            )
    })
    @PostMapping
    public ResponseEntity<ResponseSuccess<Task>> create(
            @Valid @RequestBody CreateTaskRequest request
            ){
        Task task = TaskMapper.createToDomain(request);
        Task created = service.create(task);
        return ApiResponses.created("Task "+created.getTitle()+ " created successfully", created);
    }

    @Operation(
            summary = "List tasks",
            description = "Returns a summary of all registered tasks"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of tasks found"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )

            )
    })
    @GetMapping
    public ResponseEntity<ResponseSuccess<List<TaskSummaryResponse>>> list()
    {
        return ApiResponses.success("List of tasks found", service.findAll());
    }

    @Operation(
            summary = "Find task by id",
            description = "Returns a specific task"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Task found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Task not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )

            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Task>> findById(
            @PathVariable Long id
    )
    {
        return ApiResponses.success("Task found", service.findById(id));
    }

    @Operation(
            summary = "Update task",
            description = "Returns a message with task title"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Task ${task_title} updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Task not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Request validation failed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )

            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Task>> update(
            @Valid @RequestBody UpdateTaskRequest request,
            @PathVariable Long id
            )
    {
        Task task = TaskMapper.updateToDomain(request);
        Task updated = service.update(id, task);
        return ApiResponses.success("Task "+updated.getTitle()+ " updated successfully", updated);
    }

    @Operation(
            summary = "Delete task",
            description = "Returns no content"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "no content"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Task not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )

            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    )
    {
        service.delete(id);
        return ApiResponses.noContent();
    }
}
