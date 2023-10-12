package com.shaun.todo.application.controller.api;

import com.shaun.todo.application.controller.contract.TaskContractApi;
import com.shaun.todo.application.controller.dto.request.CreateTaskRequestDto;
import com.shaun.todo.application.controller.dto.request.UpdateTaskRequestDto;
import com.shaun.todo.application.task.service.TaskService;
import com.shaun.todo.application.task.service.dto.MainResponse;
import com.shaun.todo.application.task.service.dto.TaskResponse;
import com.shaun.todo.application.task.service.dto.TaskResponseData;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
public class TaskControllerApi implements TaskContractApi {

    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskResponse> createTask(CreateTaskRequestDto createTaskRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(createTaskRequestDto));
    }

    @Override
    public ResponseEntity<TaskResponseData> findAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @Override
    public ResponseEntity<TaskResponse> findTaskByUuid(String uuid) {
        return ResponseEntity.ok(taskService.findTaskByUuid(uuid));
    }

    @Override
    public ResponseEntity<TaskResponse> findTaskByName(String name) {
        return ResponseEntity.ok(taskService.findTaskByName(name));
    }

    @Override
    public ResponseEntity<TaskResponse> updateTaskByUuid(UpdateTaskRequestDto updateTaskRequestDto) {
        return ResponseEntity.ok(taskService.updateTaskById(updateTaskRequestDto));
    }

    @Override
    public ResponseEntity<HttpStatus> deleteTaskByUuid(String uuid) {
        taskService.deleteTaskByUuid(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteTaskByName(String name) {
        taskService.deleteTaskByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}