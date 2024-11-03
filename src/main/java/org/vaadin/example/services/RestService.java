package org.vaadin.example.services;

import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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

    public <T> T postForObject(String serverPath, Object requestBody, ParameterizedTypeReference<T> responseType, boolean includeJwtToken) {
        return requestForObject(serverPath, HttpMethod.POST, requestBody, responseType, includeJwtToken);
    }

    public <T> T getForObject(String serverPath, Object requestBody, ParameterizedTypeReference<T> responseType, boolean includeJwtToken, String... requestParams) {
        return requestForObject(serverPath, HttpMethod.GET, requestBody, responseType, includeJwtToken, requestParams);
    }

    public <T> T putForObject(String serverPath, Object requestBody, ParameterizedTypeReference<T> responseType, boolean includeJwtToken){
        return requestForObject(serverPath, HttpMethod.PUT, requestBody, responseType, includeJwtToken);
    }

    private <T> T requestForObject(String serverPath, HttpMethod httpMethod, Object requestBody, ParameterizedTypeReference<T> responseType, boolean includeJwtToken, String... requestParams) {
        HttpHeaders headers = new HttpHeaders();
        if (includeJwtToken) addAuthorizationHeader(headers);
        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<T> response = restTemplate.exchange(
                serverBaseUrl + serverPath,
                httpMethod,
                entity,
                responseType,
                (Object[]) requestParams
        );

        return response.getBody();
    }

    private void addAuthorizationHeader(HttpHeaders headers) {
        headers.set("Authorization", "Bearer " + (String)VaadinSession.getCurrent().getAttribute("jwt"));
    }
}
