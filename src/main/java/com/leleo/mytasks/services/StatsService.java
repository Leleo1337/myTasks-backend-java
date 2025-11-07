package com.leleo.mytasks.services;

import com.leleo.mytasks.model.Task;
import com.leleo.mytasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatsService {

    TaskRepository taskRepository;

    public StatsService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Map<String, Long> getTaskStats() {
        Map<String, Long> stats = new HashMap<>();
        List<Task> tasks = taskRepository.findAll();

        Long total = taskRepository.count();
        Long completed = taskRepository.countByCompletedTrue();
        Long remaining = taskRepository.countByCompletedFalse();

        stats.put("total", total);
        stats.put("completed", completed);
        stats.put("remaining", remaining);

        return stats;
    }

}
