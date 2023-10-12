package com.shaun.todo.application.controller.dto.request;

import com.shaun.todo.application.task.service.dto.CreateTaskRequest;
import lombok.Data;

@Data
public class CreateTaskRequestDto implements CreateTaskRequest {
    private String name;
}
