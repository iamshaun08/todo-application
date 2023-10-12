package com.shaun.todo.application.task.entity;

import com.shaun.todo.application.task.entity.embedded.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "SHAUN_TASK")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String uuid;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;
}