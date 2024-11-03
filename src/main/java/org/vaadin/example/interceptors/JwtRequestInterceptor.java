package org.vaadin.example.interceptors;

import com.vaadin.flow.server.VaadinRequest;
import org.springframework.stereotype.Component;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

@Component
public class JwtRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public org.springframework.http.client.ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws java.io.IOException {
        VaadinRequest vaadinRequest = VaadinRequest.getCurrent();
        if (vaadinRequest != null) {
            String jwtToken = (String) vaadinRequest.getWrappedSession().getAttribute("jwt");
            if (jwtToken != null) {
                request.getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
            }
        }
        return execution.execute(request, body);
    }
}