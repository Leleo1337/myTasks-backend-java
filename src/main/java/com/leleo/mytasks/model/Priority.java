package com.leleo.mytasks.model;

public enum Priority {
    LOW, MEDIUM, HIGH;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}