package org.vaadin.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vaadin.example.dtos.user.LoginRequest;

//TODO api calls
@Service
public class UserService {

    @Autowired
    private RestService restService;

    public String login(String username, String password){
        String params = "user/login";
        return restService.postForObject(params, new LoginRequest(username, password), String.class, false);
    }
}

