package com.recommendation.service;

import com.recommendation.model.User;
import com.recommendation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUserRecommendation(int showId, User user) {
        if (user.getShows() == null) {
            user.setShows(new ArrayList<>());
        }
        user.getShows().add(showId);
        userRepository.save(user);
    }
}
