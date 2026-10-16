package com.angelly12.taskmanager.core.infrastructure.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard error response returned by the API")public record ResponseError(
        @Schema(description = "HTTP status code", example = "404")
        int status,

        @Schema(description = "Human-readable error message", example = "Task with ID 15 was not found")
        String message,

        @Schema(description = "Application-specific error code", example = "TASK_NOT_FOUND")
        String errorCode
) {
}
