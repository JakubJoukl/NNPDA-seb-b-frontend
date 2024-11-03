package org.vaadin.example.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.example.views.LoginView;
import org.vaadin.example.views.RegisterView;
import org.vaadin.example.views.ResetPasswordView;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    @Autowired
    private SecurityService securityUtils;

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    //TODO opravdu to chci? A co registrace
    private void authenticateNavigation(BeforeEnterEvent event) {
        if ((!LoginView.class.equals(event.getNavigationTarget()) &&
            !RegisterView.class.equals(event.getNavigationTarget()) &&
            !ResetPasswordView.class.equals(event.getNavigationTarget())
        ) && !securityUtils.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }
    }
}
