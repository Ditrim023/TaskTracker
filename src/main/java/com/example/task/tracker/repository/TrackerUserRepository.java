package com.example.task.tracker.repository;

import com.example.task.tracker.model.entity.TrackerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerUserRepository extends JpaRepository<TrackerUser,Long> {
    TrackerUser findTrackerUserByUsername(String username);
}
