package com.recommendation.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.recommendation.dto.*;
import com.recommendation.model.User;
import com.recommendation.repository.UserRepository;
import com.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RecommendationController {
    @Autowired
    RecommendationService recommendationService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get_show")
    public ShowDTO getRecommendationShow(Long id) {
        return recommendationService.getShow(id);
    }

    @GetMapping("/get_show_today")
    public ShowDTO getStreamingShow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String uri = "https://api.tvmaze.com/schedule/web?date=" + formatter.format(date);
        RestTemplate restTemplate = new RestTemplate();
        String shows = restTemplate.getForObject(uri, String.class);

        Type type = new TypeToken<List<ScheduleDTO>>(){}.getType();
        List<ScheduleDTO> list = new Gson().fromJson(shows, type);
        return list.get(0).get_embedded().getShow();
    }

    @PatchMapping("/reset")
    public void resetRecommendation(Long id) {
        User user = userRepository.findByPhone(id);
        user.setShows(new ArrayList<>());
        userRepository.save(user);
    }


}
