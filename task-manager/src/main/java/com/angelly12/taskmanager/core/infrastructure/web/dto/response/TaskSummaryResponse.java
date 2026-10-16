package com.angelly12.taskmanager.core.infrastructure.web.dto.response;

import com.angelly12.taskmanager.core.domain.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Summary of a task returned by the task listing endpoint")
public record TaskSummaryResponse(

        @Schema(description = "Unique task identifier", example = "15")
        Long id,

        @Schema(description = "Task title", example = "Complete technical test")
        String title,

        @Schema(description = "Current task status", example = "IN_PROGRESS")
        TaskStatus status,

        @Schema(description = "Date and time when the task was created", example = "2026-10-15T18:30:00Z")
        Instant creationDate
) {
}