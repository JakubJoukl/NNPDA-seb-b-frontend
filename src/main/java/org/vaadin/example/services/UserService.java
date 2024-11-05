package org.vaadin.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.vaadin.example.dtos.user.LoginDto;
import org.vaadin.example.dtos.user.NewPasswordDto;
import org.vaadin.example.dtos.user.RegistrationDto;
import org.vaadin.example.dtos.user.ResetPasswordDto;

//TODO api calls
@Service
public class UserService {

    @Autowired
    private RestService restService;

    public String login(LoginDto loginDto){
        String url = "user/login";
        return restService.postForObject(url, loginDto, new ParameterizedTypeReference<String>() {}, false);
    }

    public String register(RegistrationDto registrationDto) {
        String url = "user/register";
        return restService.postForObject(url, registrationDto, new ParameterizedTypeReference<String>() {}, false);
    }

    public String requestToResetPassword(ResetPasswordDto resetPasswordDto) {
        String url = "user/resetPassword";
        return restService.postForObject(url, resetPasswordDto, new ParameterizedTypeReference<String>() {}, false);
    }

    public String newPassword(NewPasswordDto newPasswordDto) {
        String url = "user/newPassword";
        return restService.postForObject(url, newPasswordDto, new ParameterizedTypeReference<String>() {}, false);
    }
}

