package com.leleo.mytasks.dtos;

import com.leleo.mytasks.model.Priority;

import java.time.LocalDateTime;
import java.util.List;

public class TaskRequest {
    private String title;
    private String description = "";
    private String priority = "LOW";
    private Boolean completed = false;
    private LocalDateTime date;
    private List<TagRequest> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<TagRequest> getTags() {
        return tags;
    }

    public void setTags(List<TagRequest> tags) {
        this.tags = tags;
    }
}
