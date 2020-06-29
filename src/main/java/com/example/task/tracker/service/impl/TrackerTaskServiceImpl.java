package com.example.task.tracker.service.impl;

import com.example.task.tracker.model.dto.TrackerTaskDto;
import com.example.task.tracker.model.entity.Status;
import com.example.task.tracker.model.entity.TrackerTask;
import com.example.task.tracker.model.entity.TrackerUser;
import com.example.task.tracker.repository.TrackerTaskRepository;
import com.example.task.tracker.repository.TrackerUserRepository;
import com.example.task.tracker.service.TrackerTaskService;
import com.example.task.tracker.service.TrackerUserService;
import com.example.task.tracker.utils.TrackerConverter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackerTaskServiceImpl implements TrackerTaskService {
    private final TrackerTaskRepository trackerTaskRepository;
    private final TrackerUserService trackerUserService;
    private TrackerConverter converter = new TrackerConverter();

    public TrackerTaskServiceImpl(TrackerTaskRepository trackerTaskRepository, TrackerUserService trackerUserService) {
        this.trackerTaskRepository = trackerTaskRepository;
        this.trackerUserService = trackerUserService;
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
        Optional<TrackerUser> userForTask = trackerUserService.findById(trackerTaskDto.getUserId());
        userForTask.ifPresent(newTask::setUser);
        trackerTaskRepository.save(newTask);
    }

    @Override
    public void updateTrackerTask(Long id, TrackerTaskDto trackerTaskDto) {
        TrackerTask task = trackerTaskRepository.getOne(id);
        task.setTitle(trackerTaskDto.getTitle());
        task.setDescription(trackerTaskDto.getDescription());
        task.setStatus(trackerTaskDto.getStatus());
        Optional<TrackerUser> userForTask = trackerUserService.findById(trackerTaskDto.getUserId());
        userForTask.ifPresent(task::setUser);
        trackerTaskRepository.save(task);
    }

    @Override
    public void updateUserForTask(Long id, String username) {
        Optional<TrackerUser> user = trackerUserService.findByUsername(username);
        TrackerTask task = trackerTaskRepository.getOne(id);
        if (user.isPresent()){
            task.setUser(user.get());
        }else{
            throw new EntityNotFoundException();
        }

        trackerTaskRepository.save(task);
    }

    @Override
    public void updateTrackerTaskStatus(Long id, String status) {
        TrackerTask task = trackerTaskRepository.getOne(id);
        task.setStatus(Status.valueOf(status));
        trackerTaskRepository.save(task);
    }

    @Override
    public void deleteTrackerTask(Long id) {
        trackerTaskRepository.deleteById(id);
    }

    @Override
    public List<TrackerTask> getAllTasks() {
        return trackerTaskRepository.findAll();
    }

    @Override
    public List<TrackerTaskDto> getAllDtoTask() {
        List<TrackerTask> taskList = getAllTasks();
        return taskList.stream().map(converter::convertTaskEntityToDto).collect(Collectors.toList());
    }
}
