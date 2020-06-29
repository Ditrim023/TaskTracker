package com.example.task.tracker.repository;

import com.example.task.tracker.model.entity.TrackerTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackerTaskRepository extends JpaRepository<TrackerTask,Long> {

}
