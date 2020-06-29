package com.example.task.tracker.repository;

import com.example.task.tracker.model.entity.Status;
import com.example.task.tracker.model.entity.TrackerTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerTaskRepository extends JpaRepository<TrackerTask,Long> {
    List<TrackerTask> findByStatus(Status status);
}
