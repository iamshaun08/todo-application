package com.shaun.todo.application.controller.contract;

import com.shaun.todo.application.controller.dto.request.CreateTaskRequestDto;
import com.shaun.todo.application.controller.dto.request.UpdateTaskRequestDto;
import com.shaun.todo.application.task.service.dto.MainResponse;
import com.shaun.todo.application.task.service.dto.TaskResponse;
import com.shaun.todo.application.task.service.dto.TaskResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequestMapping("api/v1/task")
public interface TaskContractApi {
    @PostMapping
    ResponseEntity<TaskResponse> createTask(@RequestBody CreateTaskRequestDto createTaskRequestDto);

    @GetMapping
    ResponseEntity<TaskResponseData> findAllTasks();

    @GetMapping("/{uuid}")
    ResponseEntity<TaskResponse> findTaskByUuid(@PathVariable("uuid") String uuid);

    @GetMapping("/name/{name}")
    ResponseEntity<TaskResponse> findTaskByName(@PathVariable("name") String name);

    @PostMapping("/update")
    ResponseEntity<TaskResponse> updateTaskByUuid(@RequestBody UpdateTaskRequestDto updateTaskRequestDto);

    @DeleteMapping("/{uuid}")
    ResponseEntity<HttpStatus> deleteTaskByUuid(@PathVariable("uuid") String uuid);
    @DeleteMapping("/name/{name}")
    ResponseEntity<HttpStatus> deleteTaskByName(@PathVariable("name") String name);
}