package com.example.task.tracker.service;

import com.example.task.tracker.model.dto.TrackerUserDto;
import com.example.task.tracker.model.entity.TrackerUser;

import java.util.List;

public interface TrackerUserService {
    void createTrackerUser(TrackerUserDto trackerUserDto);
    List<TrackerUser> getAllUsers();
    TrackerUser findByUsername(String username);
}
