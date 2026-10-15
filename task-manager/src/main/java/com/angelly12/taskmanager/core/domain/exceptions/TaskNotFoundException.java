package com.angelly12.taskmanager.core.domain.exceptions;

import com.angelly12.taskmanager.core.domain.enums.ErrorCode;

public class TaskNotFoundException extends DomainException{
    public TaskNotFoundException(Long id)
    {
        super(
                "Task with id " + id + " not founded",
                404,
                ErrorCode.TASK_NOT_FOUND
        );
    }
}
