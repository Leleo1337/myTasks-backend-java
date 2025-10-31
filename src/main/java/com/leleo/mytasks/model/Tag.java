package com.leleo.mytasks.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    private UUID id;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
