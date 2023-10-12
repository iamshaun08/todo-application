package com.shaun.todo.application.controller.dto.request;

import com.shaun.todo.application.task.service.dto.UpdateTaskRequest;
import lombok.Data;

@Data
public class UpdateTaskRequestDto implements UpdateTaskRequest {
    private String uuid;
    private String name;
    private String status;
}