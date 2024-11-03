package org.vaadin.example.security;

import com.vaadin.flow.server.HandlerHelper.RequestType;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.ApplicationConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public final class SecurityService {

    @Autowired
    private JwtService jwtService;

    private SecurityService() {
        // Util methods only
    }

    public boolean isFrameworkInternalRequest(HttpServletRequest request) {
        final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
        return parameterValue != null
                && Stream.of(RequestType.values())
                .anyMatch(r -> r.getIdentifier().equals(parameterValue));
    }

    public boolean isUserLoggedIn() {
        if(VaadinSession.getCurrent().getAttribute("jwt") == null) return false;
        String token = (String)VaadinSession.getCurrent().getAttribute("jwt");
        boolean isTokenValid = jwtService.validateToken(token);
        if(!isTokenValid) VaadinSession.getCurrent().setAttribute("jwt", null);
        return isTokenValid;
    }
}
