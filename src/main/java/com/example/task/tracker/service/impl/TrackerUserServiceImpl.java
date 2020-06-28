package com.example.task.tracker.service.impl;

import com.example.task.tracker.model.dto.TrackerUserDto;
import com.example.task.tracker.model.entity.TrackerUser;
import com.example.task.tracker.repository.TrackerUserRepository;
import com.example.task.tracker.service.TrackerUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackerUserServiceImpl implements TrackerUserService {
    private TrackerUserRepository trackerUserRepository;

    public TrackerUserServiceImpl(TrackerUserRepository trackerUserRepository) {
        this.trackerUserRepository = trackerUserRepository;
    }

    @Override
    public void createTrackerUser(TrackerUserDto trackerUserDto) {
//        TrackerUser newTrackerUser = new TrackerUser(trackerUserDto.getFirstName(),trackerUserDto.getLastName(),trackerUserDto.getEmail());
//        trackerUserRepository.save(newTrackerUser);
    }

    @Override
    public List<TrackerUser> getAllUsers() {
        return trackerUserRepository.findAll();
    }

    @Override
    public TrackerUser findByUsername(String username) {
        return trackerUserRepository.findTrackerUserByUsername(username);
    }
}
