package com.angelly12.taskmanager.core.domain.entity;

import com.angelly12.taskmanager.core.domain.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@Schema(description = "Domain representation of a task")
public class Task {
    @Schema(description = "Unique task identifier", example = "15")
    private Long id;

    @Schema(description = "Task title", example = "Complete technical test")
    private String title;

    @Schema(description = "Detailed description of the task", example = "Implement CRUD operations and API documentation")
    private String description;

    @Schema(description = "Current task status", example = "PENDING")
    private TaskStatus status;

    @Schema(description = "Date and time when the task was created", example = "2026-10-15T18:30:00Z")
    private Instant creationDate;

}
