package com.shaun.todo.application.task.repository;

import com.shaun.todo.application.task.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    Optional<Task> findTaskByUuid(String uuid);
    Optional<Task> findTaskByName(String name);
    boolean existsByNameAndUuidIsNot(String name, String uuid);
    boolean existsByUuid(String uuid);
    boolean existsByName(String name);
    void deleteTaskByUuid(String uuid);
    void deleteTaskByName(String name);
}