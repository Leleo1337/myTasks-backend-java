package com.leleo.mytasks.model;

public enum Priority {
    LOW, MEDIUM, HIGH;

    public String toLowerCase() {
        return name().toLowerCase();
    }

    public String toUpperCase() {
        return name().toUpperCase();
    }
}