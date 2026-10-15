package com.angelly12.taskmanager.core.domain.entity;

import com.angelly12.taskmanager.core.domain.enums.TaskStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class Task {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Instant creationDate;

}
