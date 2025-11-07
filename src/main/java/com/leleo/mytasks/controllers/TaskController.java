package com.leleo.mytasks.controllers;

import com.leleo.mytasks.dtos.TaskRequest;
import com.leleo.mytasks.model.Task;
import com.leleo.mytasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable UUID id) {
        Task task = taskService.getTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest request) {
        Task createdTask = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody TaskRequest request) {
        Task updatedTask = taskService.updateTask(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable UUID id) {
        Task deletedtask = taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedtask);
    }
}
