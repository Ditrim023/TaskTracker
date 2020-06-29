package com.example.task.tracker.service;

import com.example.task.tracker.model.dto.TrackerTaskDto;
import com.example.task.tracker.model.entity.Status;
import com.example.task.tracker.model.entity.TrackerUser;

public interface TrackerTaskService {
    TrackerTaskDto findOneById(Long id);
    void createTrackerTask(TrackerTaskDto trackerTaskDto);
    void updateTrackerTask(Long id, TrackerTaskDto trackerTaskDto);
    void updateTrackerTaskStatus(Long id, String status);
}
