package com.shaun.todo.application.task.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

public interface CreateTaskRequest {
    @NotBlank(message = "Task name cannot be empty!")
    String getName();
}