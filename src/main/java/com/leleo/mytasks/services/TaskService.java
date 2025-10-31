package com.leleo.mytasks.services;

import com.leleo.mytasks.model.Task;
import com.leleo.mytasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(String search, String priority, String status) {
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }
}
