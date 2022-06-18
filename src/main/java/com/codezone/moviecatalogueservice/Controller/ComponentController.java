package com.codezone.moviecatalogueservice.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ComponentController {

    @Lazy
    @Bean
    public RestTemplate createRestTemplateObject(){
        return new RestTemplate();
    }

    @Lazy
    @Bean
    public ObjectMapper createObjectMapperObject(){
        return new ObjectMapper();
    }
}
