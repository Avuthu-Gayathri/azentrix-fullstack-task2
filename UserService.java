package com.azentrix.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azentrix.taskmanager.entity.User;
import com.azentrix.taskmanager.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {

        User existingUser =
                repository.findByEmail(
                        user.getEmail());

        if (existingUser != null) {
            return null;
        }

        return repository.save(user);
    }

    public User findByEmail(String email) {

        return repository.findByEmail(email);

    }

    public User login(
            String email,
            String password) {

        return repository.findByEmailAndPassword(
                email,
                password);
    }
}