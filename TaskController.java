package com.azentrix.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.azentrix.taskmanager.entity.Task;
import com.azentrix.taskmanager.service.TaskService;

@Controller
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/tasks")
    public String viewTasks(
            Model model,
            HttpSession session) {

        if(session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "tasks",
                service.getAllTasks());

        return "tasks";
    }
    @GetMapping("/dashboard")
    public String dashboard(
            Model model,
            HttpSession session) {
    	if(session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }


        model.addAttribute(
                "totalTasks",
                service.getTotalTasks());

        model.addAttribute(
                "pendingTasks",
                service.getPendingTasks());

        model.addAttribute(
                "inProgressTasks",
                service.getInProgressTasks());

        model.addAttribute(
                "completedTasks",
                service.getCompletedTasks());
        model.addAttribute(
                "recentTasks",
                service.getRecentTasks());
        model.addAttribute(
                "pendingCount",
                service.getPendingCount());

        model.addAttribute(
                "progressCount",
                service.getInProgressCount());

        model.addAttribute(
                "completedCount",
                service.getCompletedCount());

        return "dashboard";
    }

    @GetMapping("/add-task")
    public String addTaskPage(
            Model model,
            HttpSession session) {

        if(session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "task",
                new Task());

        return "add-task";
    }

    @PostMapping("/save-task")
    public String saveTask(
            @ModelAttribute Task task,
            RedirectAttributes redirectAttributes) {

        service.saveTask(task);

        redirectAttributes.addFlashAttribute(
                "success",
                "Task Added Successfully!");

        return "redirect:/tasks";
    }
    @GetMapping("/edit-task/{id}")
    public String editTask(
            @PathVariable Long id,
            Model model) {

        Task task = service.getTaskById(id);

        model.addAttribute(
                "task",
                task);

        return "edit-task";
    }

    @PostMapping("/update-task")
    public String updateTask(
            @ModelAttribute Task task,
            RedirectAttributes redirectAttributes) {

        service.saveTask(task);

        redirectAttributes.addFlashAttribute(
                "success",
                "Task Updated Successfully!");

        return "redirect:/tasks";
    }
    @GetMapping("/delete-task/{id}")
    public String deleteTask(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        service.deleteTask(id);

        redirectAttributes.addFlashAttribute(
                "success",
                "Task Deleted Successfully!");

        return "redirect:/tasks";
    }
    @GetMapping("/search")
    public String searchTasks(
            @RequestParam("keyword") String keyword,
            Model model) {

        model.addAttribute(
                "tasks",
                service.searchTasks(keyword));

        return "tasks";
    }
    @GetMapping("/filter")
    public String filterTasks(
            @RequestParam String status,
            Model model) {

        if(status.equals("ALL")) {

            model.addAttribute(
                    "tasks",
                    service.getAllTasks());

        } else {

            model.addAttribute(
                    "tasks",
                    service.filterTasks(status));
        }

        return "tasks";
    }
}