package com.shaun.todo.application.task.service;

import com.shaun.todo.application.task.entity.Task;
import com.shaun.todo.application.task.entity.embedded.TaskStatus;
import com.shaun.todo.application.task.repository.TaskRepository;
import com.shaun.todo.application.task.service.dto.*;
import com.shaun.todo.application.task.service.exception.EntityNotFoundException;
import com.shaun.todo.application.task.service.exception.NotAcceptableException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest createTaskRequest) {
        if (taskRepository.existsByName(createTaskRequest.getName()))
            throw new NotAcceptableException("Task already exists!");

        Task task = new Task(
                UUID.randomUUID().toString(),
                createTaskRequest.getName(),
                TaskStatus.PENDING,
                LocalDateTime.now()
        );
        taskRepository.save(task);
        return TaskResponse.TaskResponseDto.builder()
                .uuid(task.getUuid())
                .name(task.getName())
                .status(task.getStatus().toString())
                .creationDate(task.getCreationDate().format(DateTimeFormatter.ISO_DATE))
                .build();
    }

    @Override
    public TaskResponseData findAllTasks() {
        List<TaskResponse.TaskResponseDto> taskResponses = taskRepository.findAll()
                .stream()
                .map(task -> TaskResponse.TaskResponseDto.builder()
                        .uuid(task.getUuid())
                        .name(task.getName())
                        .status(task.getStatus().toString())
                        .creationDate(task.getCreationDate().format(DateTimeFormatter.ISO_DATE))
                        .build())
                .collect(Collectors.toList());

        return TaskResponseData.TaskResponseDataDto.builder()
                .taskResponses(taskResponses)
                .totalResponses(taskResponses.size())
                .build();
    }

    @Override
    public TaskResponse findTaskByUuid(String uuid) {
        Task task = taskRepository.findTaskByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Task not found!"));

        return TaskResponse.TaskResponseDto.builder()
                .uuid(task.getUuid())
                .name(task.getName())
                .status(task.getStatus().toString())
                .creationDate(task.getCreationDate().format(DateTimeFormatter.ISO_DATE))
                .build();
    }

    @Override
    public TaskResponse findTaskByName(String name) {
        Task task = taskRepository.findTaskByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Task not found!"));

        return TaskResponse.TaskResponseDto.builder()
                .uuid(task.getUuid())
                .name(task.getName())
                .status(task.getStatus().toString())
                .creationDate(task.getCreationDate().format(DateTimeFormatter.ISO_DATE))
                .build();
    }

    @Override
    public TaskResponse updateTaskById(UpdateTaskRequest updateTaskRequest) {
        Task task = taskRepository.findTaskByUuid(updateTaskRequest.getUuid())
                .orElseThrow(() -> new EntityNotFoundException("Task not found!"));

        if (taskRepository.existsByNameAndUuidIsNot(updateTaskRequest.getName(), updateTaskRequest.getUuid()))
            throw new NotAcceptableException("Task already exists!");

        task.setName(updateTaskRequest.getName());
        task.setStatus(TaskStatus.valueOf(updateTaskRequest.getStatus()));
        taskRepository.save(task);

        return TaskResponse.TaskResponseDto.builder()
                .uuid(task.getUuid())
                .name(task.getName())
                .status(task.getStatus().toString())
                .creationDate(task.getCreationDate().format(DateTimeFormatter.ISO_DATE))
                .build();
    }

    @Override
    @Transactional
    public void deleteTaskByUuid(String uuid) {
        if (!taskRepository.existsByUuid(uuid))
            throw new EntityNotFoundException("Task not found!");
        taskRepository.deleteTaskByUuid(uuid);
    }

    @Override
    @Transactional
    public void deleteTaskByName(String name) {
        if (!taskRepository.existsByName(name))
            throw new EntityNotFoundException("Task not found!");
        taskRepository.deleteTaskByName(name);
    }
}
