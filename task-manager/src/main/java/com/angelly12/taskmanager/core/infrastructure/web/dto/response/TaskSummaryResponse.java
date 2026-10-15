package com.angelly12.taskmanager.core.infrastructure.web.dto.response;

import com.angelly12.taskmanager.core.domain.enums.TaskStatus;

import java.time.Instant;

public record TaskSummaryResponse(Long id, String title, TaskStatus status, Instant creationDate) {
}
