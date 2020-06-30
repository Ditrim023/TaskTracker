package com.example.task.tracker.controller;

import com.example.task.tracker.model.dto.TrackerTaskDto;
import com.example.task.tracker.model.entity.Status;
import com.example.task.tracker.model.entity.TrackerTask;
import com.example.task.tracker.service.TrackerTaskService;
import com.example.task.tracker.utils.TrackerConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TrackerTaskController {
    private final TrackerTaskService trackerTaskService;
    private TrackerConverter converter = new TrackerConverter();

    public TrackerTaskController(TrackerTaskService trackerTaskService) {
        this.trackerTaskService = trackerTaskService;
    }

    @GetMapping("/task/{id}")
    public TrackerTaskDto getTask(@PathVariable Long id) {
        try {
            return converter.convertTaskEntityToDto(trackerTaskService.findOneById(id));
        } catch (EntityNotFoundException ex) {
            return new TrackerTaskDto();
        }

    }

    @GetMapping("/tasks/status/{status}")
    public List<TrackerTaskDto> findTasksByStatus(@PathVariable String status) {
        List<TrackerTask> taskList = trackerTaskService.findAllByStatus(Status.valueOf(status));
        return taskList.stream().map(converter::convertTaskEntityToDto).collect(Collectors.toList());
    }

    @GetMapping("/tasks")
    public List<TrackerTaskDto> displayAllTask() {
        List<TrackerTask> taskList = trackerTaskService.getAllTasks();
        return taskList.stream().map(converter::convertTaskEntityToDto).collect(Collectors.toList());
    }

    @PostMapping("/task")
    public ResponseEntity createTask(@RequestBody TrackerTaskDto trackerTaskDto) {
        trackerTaskService.createTrackerTask(trackerTaskDto);
        return ResponseEntity.ok("Task created");
    }

    @PatchMapping("/task/{id}")
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody TrackerTaskDto trackerTaskDto) {
        trackerTaskService.updateTrackerTask(id, trackerTaskDto);
        return ResponseEntity.ok("Task updated");
    }

    @PatchMapping("/task/{id}/user/{username}")
    public ResponseEntity updateUserForTask(@PathVariable Long id, @PathVariable String username) {
        try {
            trackerTaskService.updateUserForTask(id, username);
            return ResponseEntity.ok("User for task updated");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok("User does not exist");
        }

    }

    @PatchMapping("/task/{id}/status/{status}")
    public ResponseEntity updateTask(@PathVariable Long id, @PathVariable String status) {
        if (Arrays.asList("DONE", "IN_PROGRESS", "VIEW").contains(status)) {
            trackerTaskService.updateTrackerTaskStatus(id, status);
            return ResponseEntity.ok("Status updated");
        } else {
            return ResponseEntity.ok("Status invalid");
        }

    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        trackerTaskService.deleteTrackerTask(id);
        return ResponseEntity.ok("Status deleted");
    }

}
