package com.angelly12.taskmanager.core.domain.repository;

import com.angelly12.taskmanager.core.domain.entity.Task;
import com.angelly12.taskmanager.core.infrastructure.persistence.projection.TaskSummaryProjection;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.TaskSummaryResponse;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryInterface {
    Task create(Task task);
    Task update(Long id, Task task);
    Optional<Task> findById(Long id);
    List<TaskSummaryResponse> findAllTasks();
    void delete(Long id);
}
