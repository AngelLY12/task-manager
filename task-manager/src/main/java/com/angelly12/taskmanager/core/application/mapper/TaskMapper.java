package com.angelly12.taskmanager.core.application.mapper;

import com.angelly12.taskmanager.core.domain.entity.Task;
import com.angelly12.taskmanager.core.domain.enums.TaskStatus;
import com.angelly12.taskmanager.core.infrastructure.web.dto.request.CreateTaskRequest;
import com.angelly12.taskmanager.core.infrastructure.web.dto.request.UpdateTaskRequest;

public final class TaskMapper {
    private TaskMapper(){}
    public static Task createToDomain(CreateTaskRequest request)
    {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status() != null ? request.status() : TaskStatus.PENDING)
                .build();
    }

    public static Task updateToDomain(UpdateTaskRequest request)
    {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .build();
    }
}
