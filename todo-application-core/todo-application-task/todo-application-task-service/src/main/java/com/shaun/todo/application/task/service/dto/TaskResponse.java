package com.shaun.todo.application.task.service.dto;

import lombok.Builder;
import lombok.Data;

public interface TaskResponse {
    String getUuid();
    String getName();
    String getStatus();
    String getCreationDate();
    @Data
    @Builder
    class TaskResponseDto implements TaskResponse {
        private final String uuid;
        private final String name;
        private final String status;
        private final String creationDate;
    }
}