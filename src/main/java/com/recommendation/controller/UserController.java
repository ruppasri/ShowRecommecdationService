package com.recommendation.controller;

import com.recommendation.model.User;
import com.recommendation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/user")
    public User list(Long id) {
        return userRepository.findByPhone(id);
    }

    @PostMapping("/register")
    public User register(User user) {
        try {
            User newUser = userRepository.insert(user);
            return newUser;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/login")
    public String login(Long id, String pass) {
        User user1 = userRepository.findByPhone(id);
        if (user1 == null) {
            return "User not registered";
        }
        if (user1.getPassword().equals(pass)) {
            return "Incorrect password";
        }
        return "Success";
    }

    @PatchMapping("/update")
    public User update(User user) {
        return userRepository.save(user);
    }
}
