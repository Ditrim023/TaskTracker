package com.example.task.tracker.repository;

import com.example.task.tracker.model.entity.TrackerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerRoleRepository extends JpaRepository<TrackerRole,Long> {
}
