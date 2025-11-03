package com.leleo.mytasks.services;

import com.leleo.mytasks.dto.TaskRequest;
import com.leleo.mytasks.model.Tag;
import com.leleo.mytasks.model.Task;
import com.leleo.mytasks.repository.TaskRepository;
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
        return taskRepository.findById(id).orElseThrow();
    }

    public Task createTask(TaskRequest request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setCompleted(false);
        task.setDate(request.getDate());

        List<Tag> tags = request.getTags().stream().map(tr -> {
            Tag tag = new Tag();
            tag.setText(tr.getText());
            tag.setColor(tr.getColor());
            tag.setTask(task);
            return tag;
        }).collect(Collectors.toList());

        task.setTags(tags);

        return taskRepository.save(task);
    }
}
