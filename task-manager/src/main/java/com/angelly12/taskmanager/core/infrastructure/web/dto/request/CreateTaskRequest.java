package com.angelly12.taskmanager.core.infrastructure.web.dto.request;

import com.angelly12.taskmanager.core.domain.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Data required to create a task")
public record CreateTaskRequest(
        @Schema(description = "Task title", example = "Clean the bathroom")
        @NotBlank
        @Size(max=150)
        String title,

        @Schema(description = "Task description", example = "Use bleach")
        String description,
        @Schema(description = "Initial task status", example = "PENDING")
        TaskStatus status
) {
}
