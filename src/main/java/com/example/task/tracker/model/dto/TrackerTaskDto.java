package com.example.task.tracker.model.dto;

import com.example.task.tracker.model.entity.Status;

public class TrackerTaskDto {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private long userId;

    public TrackerTaskDto() {
    }

    public TrackerTaskDto(String title, String description, Status status, int userId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
