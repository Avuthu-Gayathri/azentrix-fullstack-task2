package com.azentrix.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.azentrix.taskmanager.entity.User;
import com.azentrix.taskmanager.service.UserService;

import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String registerPage(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String saveUser(
            @ModelAttribute User user,
            Model model) {

        service.saveUser(user);

        model.addAttribute(
                "success",
                "Registration Successful");

        model.addAttribute(
                "user",
                new User());

        return "register";
    }
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }
    @PostMapping("/login")
    public String loginUser(
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        User user = service.login(email, password);

        if(user != null) {

            session.setAttribute(
                    "loggedUser",
                    user);

            return "redirect:/dashboard";
        }

        model.addAttribute(
                "error",
                "Invalid Email or Password");

        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
    @GetMapping("/test")
    public String test() {
        return "login";
    }
}