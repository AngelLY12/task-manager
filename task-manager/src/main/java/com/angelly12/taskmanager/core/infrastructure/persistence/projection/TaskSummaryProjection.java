package com.angelly12.taskmanager.core.infrastructure.persistence.projection;

import com.angelly12.taskmanager.core.domain.enums.TaskStatus;

import java.time.Instant;

public interface TaskSummaryProjection {
    Long getId();
    String getTitle();
    TaskStatus getStatus();
    Instant getCreationDate();
}
