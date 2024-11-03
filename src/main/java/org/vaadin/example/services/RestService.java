package org.vaadin.example.services;

import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend.url}")
    private String serverBaseUrl;

    public <T> T postForObject(String serverPath, Object requestParams, Class<T> resultClass, boolean includeJwtToken) {
        HttpHeaders headers = new HttpHeaders();
        if(includeJwtToken) addAuthorizationHeader(headers);
        HttpEntity<Object> entity = new HttpEntity<>(requestParams, headers);

        ResponseEntity<T> response = restTemplate.exchange(
                serverBaseUrl + serverPath,
                HttpMethod.POST,
                entity,
                resultClass
        );

        return response.getBody();
    }

    public <T> T getForObject(String serverPath, Class<T> resultClass, boolean includeJwtToken, String... requestParams) {
        HttpHeaders headers = new HttpHeaders();
        if(includeJwtToken) addAuthorizationHeader(headers);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(
                serverBaseUrl + serverPath,
                HttpMethod.GET,
                entity,
                resultClass,
                (Object[]) requestParams
        );

        return response.getBody();
    }

    private void addAuthorizationHeader(HttpHeaders headers) {
        headers.set("Authorization", "Bearer " + (String)VaadinSession.getCurrent().getAttribute("jwt"));
    }
}
