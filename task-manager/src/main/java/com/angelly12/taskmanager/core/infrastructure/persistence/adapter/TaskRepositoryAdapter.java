package com.angelly12.taskmanager.core.infrastructure.persistence.adapter;

import com.angelly12.taskmanager.core.domain.entity.Task;
import com.angelly12.taskmanager.core.domain.exceptions.TaskNotFoundException;
import com.angelly12.taskmanager.core.domain.repository.TaskRepositoryInterface;
import com.angelly12.taskmanager.core.infrastructure.persistence.entity.TaskEntity;
import com.angelly12.taskmanager.core.infrastructure.persistence.mapper.TaskMapper;
import com.angelly12.taskmanager.core.infrastructure.persistence.projection.TaskSummaryProjection;
import com.angelly12.taskmanager.core.infrastructure.persistence.repository.TaskJpaRepository;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.TaskSummaryResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryAdapter implements TaskRepositoryInterface {

    private final TaskJpaRepository repository;
    public TaskRepositoryAdapter(TaskJpaRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Task create(Task task) {
        TaskEntity entity = TaskMapper.domainToEntity(task);
        TaskEntity saved = repository.save(entity);
        return TaskMapper.entityToDomain(saved);
    }

    @Override
    public Task update(Long id, Task task) {

        TaskEntity entity = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        TaskMapper.updateEntity(entity, task);
        TaskEntity saved = repository.save(entity);
        return TaskMapper.entityToDomain(saved);
    }

    @Override
    public Optional<Task> findById(Long id) {

        return repository.findById(id)
                .map(TaskMapper::entityToDomain);
    }

    @Override
    public List<TaskSummaryResponse> findAllTasks() {
        return repository.findAllProjectedBy()
                .stream()
                .map(task -> new TaskSummaryResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getStatus(),
                        task.getCreationDate()
                ))
                .toList();
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id))
        {
            throw new TaskNotFoundException(id);
        }

        repository.deleteById(id);
    }
}
