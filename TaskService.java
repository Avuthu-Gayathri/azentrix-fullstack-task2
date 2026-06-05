package com.azentrix.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azentrix.taskmanager.entity.Task;
import com.azentrix.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task task) {

        return repository.save(task);
    }

    public List<Task> getAllTasks() {

        return repository.findAll();
    }

    public Task getTaskById(Long id) {

        return repository.findById(id).orElse(null);
    }

    public void deleteTask(Long id) {

        repository.deleteById(id);
        
    }
    public long getTotalTasks() {
        return repository.count();
    }
    public long getPendingTasks() {

        return repository.countByStatus("PENDING");
    }

    public long getInProgressTasks() {

        return repository.countByStatus("IN PROGRESS");
    }

    public long getCompletedTasks() {

        return repository.countByStatus("COMPLETED");
    }
    public List<Task> searchTasks(String keyword) {

        return repository.findByTitleContainingIgnoreCase(keyword);
    }
    public List<Task> filterTasks(String status) {

        return repository.findByStatus(status);
    }
    public List<Task> getRecentTasks() {

        return repository.findTop5ByOrderByIdDesc();
    }
    public long getPendingCount() {
        return repository.findByStatus("PENDING").size();
    }

    public long getInProgressCount() {
        return repository.findByStatus("IN PROGRESS").size();
    }

    public long getCompletedCount() {
        return repository.findByStatus("COMPLETED").size();
    }
    
}