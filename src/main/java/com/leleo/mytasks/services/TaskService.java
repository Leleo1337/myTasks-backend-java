package com.leleo.mytasks.services;

import com.leleo.mytasks.dtos.TaskRequest;
import com.leleo.mytasks.exceptions.NotFoundException;
import com.leleo.mytasks.exceptions.ValidationException;
import com.leleo.mytasks.model.Priority;
import com.leleo.mytasks.model.Tag;
import com.leleo.mytasks.model.Task;
import com.leleo.mytasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(String searchQuery, String priority, String status) {
        List<Task> tasks = taskRepository.findAllByOrderByCreatedAtAsc();
        Map<String, String> filters = new HashMap<>();
        if (!searchQuery.isBlank()) {
            return taskRepository.findByTitleContainingIgnoreCase(searchQuery);
        }

        return tasks.stream()
                .filter(task -> priority.isBlank() ||
                        priority.equals("all") ||
                        (task.getPriority() != null && task.getPriority().name().equalsIgnoreCase(priority)))
                .filter(task -> status.isBlank() ||
                        status.equals("all") ||
                        (status.equals("completed") && task.getCompleted()) ||
                        (status.equals("uncompleted") && !task.getCompleted()))
                .toList();
    }

    public Task getTask(UUID id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
    }

    public Task createTask(TaskRequest request) {
        Map<String, String> errors = new HashMap<>();

        if (request.getTitle() == null) {
            errors.put("title", "Title is required");
        }
        try {
            Priority.valueOf(request.getPriority().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println();
            errors.put("priority", "Priority must be LOW, MEDIUM, HIGH");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("Invalid fields", errors);
        }

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(Priority.valueOf(request.getPriority().toUpperCase()));
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
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
        if (request.getTitle() != null && !request.getTitle().isBlank()) task.setTitle(request.getTitle());
        if (request.getDescription() != null && !request.getDescription().isBlank())
            task.setDescription(request.getDescription());
        if (request.getPriority() != null) task.setPriority(Priority.valueOf(request.getPriority().toUpperCase()));
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
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
        taskRepository.deleteById(id);
        return task;
    }
}

