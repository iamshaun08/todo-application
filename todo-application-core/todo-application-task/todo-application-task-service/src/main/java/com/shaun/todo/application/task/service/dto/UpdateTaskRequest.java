package com.shaun.todo.application.task.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public interface UpdateTaskRequest {
    @NotBlank(message = "Task ID cannot be empty!")
    String getUuid();
    @NotBlank(message = "Task name cannot be empty!")
    String getName();
    @NotBlank(message = "Task status cannot be empty")
    @Pattern(regexp = "^(PENDING|COMPLETED)$", message = "Invalid task status!")
    String getStatus();
}