package com.leleo.mytasks.controllers;

import com.leleo.mytasks.model.Task;
import com.leleo.mytasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/v1/tasks") // define o prefixo para todos os endpoints
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(
    ) {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    };

}
