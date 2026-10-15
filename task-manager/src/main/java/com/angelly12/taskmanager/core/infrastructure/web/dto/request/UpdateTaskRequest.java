package com.angelly12.taskmanager.core.infrastructure.web.dto.request;

import com.angelly12.taskmanager.core.domain.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTaskRequest(
        @NotBlank
        @Size(max=150)
        String title,
        String description,
        @NotNull
        TaskStatus status
) {
}
