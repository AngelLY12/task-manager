package com.angelly12.taskmanager.core.infrastructure.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard successful response returned by the API")
public record ResponseSuccess<T>(

        @Schema(description = "Indicates whether the operation was successful", example = "true")
        boolean success,

        @Schema(description = "Human-readable result message", example = "Task created successfully")
        String message,

        @Schema(description = "Response payload; its structure depends on the operation")
        T data
) {
}
