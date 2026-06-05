package com.azentrix.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azentrix.taskmanager.entity.Task;

public interface TaskRepository
        extends JpaRepository<Task, Long> {

    long countByStatus(String status);

    List<Task> findByTitleContainingIgnoreCase(String keyword);
    List<Task> findByStatus(String status);
    List<Task> findTop5ByOrderByIdDesc();
}