package com.recommendation.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.recommendation.dto.CashCreditDto;
import com.recommendation.dto.CrewCreditDto;
import com.recommendation.dto.ShowDTO;
import com.recommendation.model.User;
import com.recommendation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public ShowDTO getShow(Long id) {
        try {
            User user = userRepository.findByPhone(id);
            int[] crew = user.getCrew();
            int[] cast = user.getCast();
            RestTemplate restTemplate = new RestTemplate();
            ArrayList<Integer> showList = new ArrayList<>();
            String uri = "";
            for (int i : cast) {
                uri = "https://api.tvmaze.com/people/" + i + "/castcredits?embed=show";
                String cashCredit = restTemplate.getForObject(uri, String.class);

                Type type = new TypeToken<List<CashCreditDto>>(){}.getType();
                List<CashCreditDto> list = new Gson().fromJson(cashCredit, type);
                for (CashCreditDto castDto : list) {
                    showList.add(castDto.get_embedded().getShow().getId());
                }
            }
            for (int i: crew) {
                uri = "https://api.tvmaze.com/people/" + i + "/crewcredits?embed=show";
                String crewCredit = restTemplate.getForObject(uri, String.class);

                Type type = new TypeToken<List<CrewCreditDto>>(){}.getType();
                List<CrewCreditDto> list = new Gson().fromJson(crewCredit, type);
                for (CrewCreditDto crewDto : list) {
                    showList.add(crewDto.get_embedded().getShow().getId());
                }
            }
            ArrayList<Integer> showIds = user.getShows();
            if (showIds != null && showIds.size() != 0 && showList.size() != 0 ) {
                showList.removeAll(showIds);
            }
            if (showList.size() == 0) {
                return null;
            }
            int sid = showList.get(0);
            ShowDTO showDTO = getShowFromCache(sid);
            if (showDTO == null) return null;
            userService.addUserRecommendation(sid, user);
            return showDTO;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Cacheable(value = "show", key = "id")
    public ShowDTO getShowFromCache(int id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ArrayList<Integer> showList = new ArrayList<>();
            String uri = "https://api.tvmaze.com/shows/" + id;
            ShowDTO showDetials = restTemplate.getForObject(uri, ShowDTO.class);
            return showDetials;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
