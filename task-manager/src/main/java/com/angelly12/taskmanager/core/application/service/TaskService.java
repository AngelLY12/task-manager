package com.angelly12.taskmanager.core.application.service;

import com.angelly12.taskmanager.core.domain.entity.Task;
import com.angelly12.taskmanager.core.domain.exceptions.TaskNotFoundException;
import com.angelly12.taskmanager.core.domain.repository.TaskRepositoryInterface;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.TaskSummaryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepositoryInterface repository;
    public TaskService(TaskRepositoryInterface repository)
    {
        this.repository = repository;
    }

    public Task create(Task task)
    {
        return repository.create(task);
    }

    public Task update(Long id, Task task)
    {
        return repository.update(id, task);
    }

    public List<TaskSummaryResponse> findAll()
    {
        return repository.findAllTasks();
    }

    public Task findById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void delete(Long id)
    {
        repository.delete(id);
    }
}
