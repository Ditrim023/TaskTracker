package com.example.task.tracker.service.impl;

import com.example.task.tracker.model.dto.TrackerUserDto;
import com.example.task.tracker.model.entity.TrackerUser;
import com.example.task.tracker.repository.TrackerRoleRepository;
import com.example.task.tracker.repository.TrackerUserRepository;
import com.example.task.tracker.service.TrackerUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackerUserServiceImpl implements TrackerUserService {
    private TrackerUserRepository trackerUserRepository;
    private TrackerRoleRepository trackerRoleRepository;

    public TrackerUserServiceImpl(TrackerUserRepository trackerUserRepository, TrackerRoleRepository trackerRoleRepository) {
        this.trackerUserRepository = trackerUserRepository;
        this.trackerRoleRepository = trackerRoleRepository;
    }

    @Override
    public void createTrackerUser(TrackerUserDto trackerUserDto) {
        TrackerUser newTrackerUser = new TrackerUser(trackerUserDto.getUsername(), trackerUserDto.getFirstName(), trackerUserDto.getLastName(), trackerUserDto.getEmail());
        newTrackerUser.setRole(trackerRoleRepository.getOne(2L));
        newTrackerUser.setPassword(new BCryptPasswordEncoder(10).encode(trackerUserDto.getPassword()));
        trackerUserRepository.save(newTrackerUser);
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
