package com.leleo.mytasks.dto;

public class TagRequest {
    private String text;
    private String color;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
