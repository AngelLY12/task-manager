package com.angelly12.taskmanager.core.domain.repository;

import com.angelly12.taskmanager.core.domain.entity.Task;
import com.angelly12.taskmanager.core.infrastructure.web.dto.request.CreateTaskRequest;

public interface TaskRepositoryInterface {
    Task create(CreateTaskRequest task);
}
