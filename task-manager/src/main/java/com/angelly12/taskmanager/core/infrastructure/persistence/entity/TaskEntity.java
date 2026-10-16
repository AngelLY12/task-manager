package com.angelly12.taskmanager.core.infrastructure.persistence.entity;

import com.angelly12.taskmanager.core.domain.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TaskStatus status;

    @Column(nullable = false, updatable = false)
    private Instant creationDate;

    @PrePersist
    protected void onCreate(){
        if(creationDate == null)
        {
            creationDate = Instant.now();
        }
    }
}
