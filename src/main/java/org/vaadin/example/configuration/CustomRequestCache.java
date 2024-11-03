package org.vaadin.example.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.vaadin.example.security.SecurityService;

class CustomRequestCache extends HttpSessionRequestCache {

    @Autowired
    private SecurityService securityService;

    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        if (!securityService.isFrameworkInternalRequest(request)) {
            super.saveRequest(request, response);
        }
    }
}