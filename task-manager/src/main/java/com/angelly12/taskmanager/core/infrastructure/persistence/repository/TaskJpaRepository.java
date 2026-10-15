package com.angelly12.taskmanager.core.infrastructure.persistence.repository;

import com.angelly12.taskmanager.core.infrastructure.persistence.entity.TaskEntity;
import com.angelly12.taskmanager.core.infrastructure.persistence.projection.TaskSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskSummaryProjection> findAllProjectedBy();
}
