package com.example.task.tracker.controller;

import com.example.task.tracker.model.dto.TrackerTaskDto;
import com.example.task.tracker.model.entity.Status;
import com.example.task.tracker.service.TrackerTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class TrackerTaskController {
    private final TrackerTaskService trackerTaskService;

    public TrackerTaskController(TrackerTaskService trackerTaskService) {
        this.trackerTaskService = trackerTaskService;
    }

    @GetMapping("/task/{id}")
    public TrackerTaskDto getTask(@PathVariable Long id) {
        return trackerTaskService.findOneById(id);
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

    @PatchMapping("/task/{id}/status/{status}")
    public ResponseEntity updateTask(@PathVariable Long id, @PathVariable String status) {
        if (Arrays.asList("DONE", "IN_PROGRESS", "VIEW").contains(status)){
            trackerTaskService.updateTrackerTaskStatus(id, status);
            return ResponseEntity.ok("Status updated");
        }else{
            return ResponseEntity.ok("Status invalid");
        }

    }
}
