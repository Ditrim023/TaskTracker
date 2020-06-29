package com.example.task.tracker.service;

import com.example.task.tracker.model.dto.TrackerTaskDto;
import com.example.task.tracker.model.entity.Status;
import com.example.task.tracker.model.entity.TrackerTask;

import java.util.List;

public interface TrackerTaskService {
    TrackerTaskDto findOneById(Long id);

    void createTrackerTask(TrackerTaskDto trackerTaskDto);

    void updateTrackerTask(Long id, TrackerTaskDto trackerTaskDto);

    void updateUserForTask(Long id, String username);

    void updateTrackerTaskStatus(Long id, String status);

    void deleteTrackerTask(Long id);

    List<TrackerTask> getAllTasks();

    List<TrackerTaskDto> getAllDtoTask();

    List<TrackerTaskDto> findAllByStatus(Status status);
}
