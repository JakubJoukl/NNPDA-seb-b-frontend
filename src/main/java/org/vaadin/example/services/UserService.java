package org.vaadin.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.vaadin.example.dtos.user.LoginRequest;

import java.util.List;

//TODO api calls
@Service
public class UserService {

    @Autowired
    private RestService restService;

    public String login(String username, String password){
        String params = "user/login";
        return restService.postForObject(params, new LoginRequest(username, password), new ParameterizedTypeReference<String>() {}, false);
    }
}

