package com.recommendation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.recommendation.dto.ScheduleDTO;
import com.recommendation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationInitializer {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    UserRepository userRepository;

    @EventListener
    void handleAfterUserRemoved(ApplicationReadyEvent event) {
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<Integer> showList = new ArrayList<>();
        String uri = "https://api.tvmaze.com/schedule";
        String schedules = restTemplate.getForObject(uri, String.class);

        Type type = new TypeToken<List<ScheduleDTO>>(){}.getType();
        List<ScheduleDTO> list = new Gson().fromJson(schedules, type);
        for (ScheduleDTO schedule : list) {
            cacheManager.getCache("show").put(schedule.getShow().getId(), schedule.getShow());
        }
    }

}
