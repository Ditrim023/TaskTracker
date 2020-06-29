package com.example.task.tracker.service.impl;

import com.example.task.tracker.model.dto.TrackerTaskDto;
import com.example.task.tracker.model.entity.Status;
import com.example.task.tracker.model.entity.TrackerTask;
import com.example.task.tracker.model.entity.TrackerUser;
import com.example.task.tracker.repository.TrackerTaskRepository;
import com.example.task.tracker.repository.TrackerUserRepository;
import com.example.task.tracker.service.TrackerTaskService;
import com.example.task.tracker.utils.TrackerConverter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackerTaskServiceImpl implements TrackerTaskService {
    private final TrackerTaskRepository trackerTaskRepository;
    private final TrackerUserRepository trackerUserRepository;
    private TrackerConverter converter = new TrackerConverter();

    public TrackerTaskServiceImpl(TrackerTaskRepository trackerTaskRepository, TrackerUserRepository trackerUserRepository) {
        this.trackerTaskRepository = trackerTaskRepository;
        this.trackerUserRepository = trackerUserRepository;
    }

    @Override
    public TrackerTaskDto findOneById(Long id) {
        return converter.convertTaskEntityToDto(trackerTaskRepository.getOne(id));
    }

    @Override
    public void createTrackerTask(TrackerTaskDto trackerTaskDto) {
        TrackerTask newTask = new TrackerTask();
        newTask.setTitle(trackerTaskDto.getTitle());
        newTask.setDescription(trackerTaskDto.getDescription());
        newTask.setStatus(trackerTaskDto.getStatus());
        Optional<TrackerUser> userForTask = trackerUserRepository.findById(trackerTaskDto.getUserId());
        userForTask.ifPresent(newTask::setUser);
        trackerTaskRepository.save(newTask);
    }

    @Override
    public void updateTrackerTask(Long id, TrackerTaskDto trackerTaskDto) {
        TrackerTask task = trackerTaskRepository.getOne(id);
        task.setTitle(trackerTaskDto.getTitle());
        task.setDescription(trackerTaskDto.getDescription());
        task.setStatus(trackerTaskDto.getStatus());
        Optional<TrackerUser> userForTask = trackerUserRepository.findById(trackerTaskDto.getUserId());
        userForTask.ifPresent(task::setUser);
        trackerTaskRepository.save(task);
    }

    @Override
    public void updateTrackerTaskStatus(Long id, String status) {
        TrackerTask task = trackerTaskRepository.getOne(id);
        task.setStatus(Status.valueOf(status));
        trackerTaskRepository.save(task);
    }
}
