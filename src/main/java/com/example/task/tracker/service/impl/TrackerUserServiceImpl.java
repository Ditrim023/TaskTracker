package com.example.task.tracker.service.impl;

import com.example.task.tracker.model.dto.NewTrackerUserDto;
import com.example.task.tracker.model.dto.TrackerUserDto;
import com.example.task.tracker.model.entity.TrackerUser;
import com.example.task.tracker.repository.TrackerRoleRepository;
import com.example.task.tracker.repository.TrackerUserRepository;
import com.example.task.tracker.service.TrackerUserService;
import com.example.task.tracker.utils.TrackerConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackerUserServiceImpl implements TrackerUserService {
    private TrackerUserRepository trackerUserRepository;
    private TrackerRoleRepository trackerRoleRepository;
    private TrackerConverter trackerConverter = new TrackerConverter();

    public TrackerUserServiceImpl(TrackerUserRepository trackerUserRepository, TrackerRoleRepository trackerRoleRepository) {
        this.trackerUserRepository = trackerUserRepository;
        this.trackerRoleRepository = trackerRoleRepository;
    }

    @Override
    public void createTrackerUser(NewTrackerUserDto trackerUserDto) {
        TrackerUser newTrackerUser = new TrackerUser(trackerUserDto.getUsername(), trackerUserDto.getFirstName(), trackerUserDto.getLastName(), trackerUserDto.getEmail());
        newTrackerUser.setRole(trackerRoleRepository.getOne(2L));
        newTrackerUser.setPassword(new BCryptPasswordEncoder(10).encode(trackerUserDto.getPassword()));
        trackerUserRepository.save(newTrackerUser);
    }

    @Override
    public void updateTrackerUser(Long id, NewTrackerUserDto trackerUserDto) {
        TrackerUser user = trackerUserRepository.getOne(1L);
        user.setUsername(trackerUserDto.getUsername());
        user.setFirstName(trackerUserDto.getFirstName());
        user.setLastName(trackerUserDto.getLastName());
        user.setEmail(trackerUserDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder(10).encode(trackerUserDto.getPassword()));
        trackerUserRepository.save(user);
    }

    @Override
    public void deleteTrackerUser(Long id) {
        trackerUserRepository.deleteById(id);
    }

    @Override
    public List<TrackerUser> getAllUsers() {
        return trackerUserRepository.findAll();
    }

    @Override
    public List<TrackerUserDto> getAllDtoUser() {
        List<TrackerUser> userList = getAllUsers();
        return userList.stream().map(trackerConverter::convertUserEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<TrackerUserDto> findAllOrderByDateCreatedDesc() {
        List<TrackerUser> userList = trackerUserRepository.findAllByOrderByDateCreateDesc();
        return userList.stream().map(trackerConverter::convertUserEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<TrackerUser> findByUsername(String username) {
        return Optional.ofNullable(trackerUserRepository.findTrackerUserByUsername(username));
    }

    @Override
    public Optional<TrackerUser> findById(Long id) {
        return trackerUserRepository.findById(id);
    }

}
