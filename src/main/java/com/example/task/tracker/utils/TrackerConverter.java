package com.example.task.tracker.utils;

import com.example.task.tracker.model.dto.TrackerUserDto;
import com.example.task.tracker.model.entity.TrackerUser;

public class TrackerConverter {
    public TrackerUserDto convertEntityToDto(TrackerUser trackerUser){
        TrackerUserDto userDto = new TrackerUserDto();
        userDto.setId(trackerUser.getUserId());
        userDto.setUsername(trackerUser.getUsername());
        userDto.setFirstName(trackerUser.getFirstName());
        userDto.setLastName(trackerUser.getLastName());
        userDto.setEmail(trackerUser.getEmail());
        userDto.setRole(trackerUser.getRole().getName());
        return userDto;
    }
}
