package com.shaun.todo.application.task.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


public interface TaskResponseData {

    List<TaskResponse.TaskResponseDto> getTaskResponses();
    long getTotalResponses();

    @Data
    @Builder
    class TaskResponseDataDto implements TaskResponseData {
        private final List<TaskResponse.TaskResponseDto> taskResponses;
        private final long totalResponses;
    }
}