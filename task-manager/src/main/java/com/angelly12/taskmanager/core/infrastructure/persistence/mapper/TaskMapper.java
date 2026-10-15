package com.angelly12.taskmanager.core.infrastructure.persistence.mapper;

import com.angelly12.taskmanager.core.domain.entity.Task;
import com.angelly12.taskmanager.core.domain.enums.TaskStatus;
import com.angelly12.taskmanager.core.infrastructure.persistence.entity.TaskEntity;
import com.angelly12.taskmanager.core.infrastructure.web.dto.request.CreateTaskRequest;
import com.angelly12.taskmanager.core.infrastructure.web.dto.request.UpdateTaskRequest;

public final class TaskMapper {

    private TaskMapper(){}

    public static Task entityToDomain(TaskEntity entity)
    {
        return Task.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .creationDate(entity.getCreationDate())
                .build();
    }

    public static TaskEntity domainToEntity(Task domain)
    {
        return TaskEntity.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .description(domain.getDescription())
                .status(domain.getStatus())
                .creationDate(domain.getCreationDate())
                .build();
    }

    public static TaskEntity createToEntity(CreateTaskRequest request)
    {
        return TaskEntity.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status() != null ? request.status() : TaskStatus.PENDING)
                .build();
    }

    public static void updateEntity(TaskEntity entity, UpdateTaskRequest request)
    {
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setStatus(request.status());
    }

}
