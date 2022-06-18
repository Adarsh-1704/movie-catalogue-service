package com.codezone.moviecatalogueservice.Controller;

import com.codezone.moviecatalogueservice.MovieList.MovieDataList;
import com.codezone.moviecatalogueservice.MovieList.MovieInfo;
import com.codezone.moviecatalogueservice.MovieList.RatingData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.sound.midi.Soundbank;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    RestTemplate restTemplate;
    ObjectMapper objectMapper;

    @Autowired
    Controller(RestTemplate r,ObjectMapper o){
        this.restTemplate=r;
        this.objectMapper=o;
    }

    @GetMapping("/Movie")
    public List<MovieDataList> getMovieList(@RequestParam String userId) throws URISyntaxException, JsonProcessingException {

        String url = "http://localhost:8081/Rating?userId="+userId;
        URI uri = new URI(url);
        String jsonString = restTemplate.getForObject(uri,String.class);

        List<RatingData> list1 = new ArrayList<>();
        list1 = objectMapper.readValue(jsonString,list1.getClass());
        List<MovieDataList> lists = new ArrayList<>();
        for(int i = 0; i< list1.size(); i++)
        {
            RatingData r = objectMapper.readValue(objectMapper.writeValueAsString(list1.get(i)),RatingData.class);
            Integer id = r.getMovieId();
            String s = Integer.toString(id);
            double rate = r.getRating();
            String urll = "http://localhost:8082/Data?movieId="+s;
            URI urii = new URI(urll);
            String jsonStr = restTemplate.getForObject(urii,String.class);

            MovieInfo movieInfo = new MovieInfo();
            movieInfo = objectMapper.readValue(jsonStr,movieInfo.getClass());
            MovieDataList movieDataList = new MovieDataList(id, movieInfo.getMovieName(), movieInfo.getYear(),rate);
            lists.add(movieDataList);
        }
        return lists;
    }
}
