package com.leleo.mytasks.services;

import com.leleo.mytasks.dto.TaskRequest;
import com.leleo.mytasks.model.Tag;
import com.leleo.mytasks.model.Task;
import com.leleo.mytasks.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(UUID id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public Task createTask(TaskRequest request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setCompleted(false);
        task.setDate(request.getDate());

        if (request.getTags() != null) {
            List<Tag> tags = request.getTags().stream().map(tr -> {
                Tag tag = new Tag();
                tag.setText(tr.getText());
                tag.setColor(tr.getColor());
                tag.setTask(task);
                return tag;
            }).collect(Collectors.toList());
            task.setTags(tags);
        }

        return taskRepository.save(task);
    }

    public Task updateTask(UUID id, TaskRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        if (request.getTitle() != null && !request.getTitle().isBlank()) task.setTitle(request.getTitle());
        if (request.getDescription() != null && !request.getDescription().isBlank())
            task.setDescription(request.getDescription());
        if (request.getPriority() != null) task.setPriority(request.getPriority());
        if (request.getCompleted() != null) task.setCompleted(request.getCompleted());
        if (request.getDate() != null) task.setDate(request.getDate());
        if (request.getTags() != null) {
            List<Tag> newTags = request.getTags().stream().map(tr -> {
                Tag tag = new Tag();
                tag.setId(tr.getId());
                tag.setText(tr.getText());
                tag.setColor(tr.getColor());
                tag.setTask(task);
                return tag;
            }).toList();
            task.getTags().clear();
            task.getTags().addAll(newTags);
        }

        taskRepository.save(task);
        return task;
    }

    public Task deleteTask(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        taskRepository.deleteById(id);
        return task;
    }
}

