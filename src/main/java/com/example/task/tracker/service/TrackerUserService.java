package com.example.task.tracker.service;

import com.example.task.tracker.model.dto.NewTrackerUserDto;
import com.example.task.tracker.model.dto.TrackerUserDto;
import com.example.task.tracker.model.entity.TrackerUser;

import java.util.List;

public interface TrackerUserService {
    void createTrackerUser(NewTrackerUserDto trackerUserDto);
    void updateTrackerUser(Long id,NewTrackerUserDto trackerUserDto);
    void deleteTrackerUser(Long id);
    List<TrackerUser> getAllUsers();
    List<TrackerUserDto> getAllDtoUser();
    TrackerUser findByUsername(String username);
}
