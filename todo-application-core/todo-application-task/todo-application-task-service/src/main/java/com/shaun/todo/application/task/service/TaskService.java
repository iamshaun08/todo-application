package com.shaun.todo.application.task.service;

import com.shaun.todo.application.task.service.dto.*;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest createTaskRequest);
    TaskResponseData findAllTasks();
    TaskResponse findTaskByUuid(String uuid);
    TaskResponse findTaskByName(String name);
    TaskResponse updateTaskById(UpdateTaskRequest updateTaskRequest);
    void deleteTaskByUuid(String id);
    void deleteTaskByName(String name);
}