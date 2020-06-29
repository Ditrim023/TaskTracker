package com.example.task.tracker.service;

import com.example.task.tracker.model.dto.NewTrackerUserDto;
import com.example.task.tracker.model.dto.TrackerUserDto;
import com.example.task.tracker.model.entity.TrackerUser;

import java.util.List;
import java.util.Optional;

public interface TrackerUserService {
    void createTrackerUser(NewTrackerUserDto trackerUserDto);
    void updateTrackerUser(Long id,NewTrackerUserDto trackerUserDto);
    void deleteTrackerUser(Long id);
    List<TrackerUser> getAllUsers();
    List<TrackerUserDto> getAllDtoUser();
    List<TrackerUserDto> findAllOrderByDateCreatedDesc();
    Optional<TrackerUser> findByUsername(String username);
    Optional<TrackerUser> findById(Long id);
}
