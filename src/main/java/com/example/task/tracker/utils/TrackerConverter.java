package com.example.task.tracker.utils;

import com.example.task.tracker.model.dto.TrackerTaskDto;
import com.example.task.tracker.model.dto.TrackerUserDto;
import com.example.task.tracker.model.entity.TrackerTask;
import com.example.task.tracker.model.entity.TrackerUser;

public class TrackerConverter {
    public TrackerUserDto convertUserEntityToDto(TrackerUser trackerUser) {
        TrackerUserDto userDto = new TrackerUserDto();
        userDto.setId(trackerUser.getUserId());
        userDto.setUsername(trackerUser.getUsername());
        userDto.setFirstName(trackerUser.getFirstName());
        userDto.setLastName(trackerUser.getLastName());
        userDto.setEmail(trackerUser.getEmail());
        userDto.setRole(trackerUser.getRole().getName());
       if (trackerUser.getTaskList().isEmpty()){
           userDto.setCountTask(0);
       }else{
           userDto.setCountTask(trackerUser.getTaskList().size());
       }
        return userDto;
    }

    public TrackerTaskDto convertTaskEntityToDto(TrackerTask trackerTask){
        TrackerTaskDto taskDto = new TrackerTaskDto();
        taskDto.setTitle(trackerTask.getTitle());
        taskDto.setDescription(trackerTask.getDescription());
        taskDto.setStatus(trackerTask.getStatus());
        taskDto.setUserId(trackerTask.getUser().getUserId());
        return taskDto;
    }

}
