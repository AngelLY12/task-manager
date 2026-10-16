package com.angelly12.taskmanager.core.infrastructure.web.dto.request;

import com.angelly12.taskmanager.core.domain.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Data required to update a task")
public record UpdateTaskRequest(
        @Schema(description = "Task title", example = "Clean the bathroom")
        @NotBlank
        @Size(max=150)
        String title,

        @Schema(description = "Task description", example = "Use bleach")
        String description,

        @Schema(description = "Task status", example = "PENDING")
        @NotNull
        TaskStatus status
) {
}
