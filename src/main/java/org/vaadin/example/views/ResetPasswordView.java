package org.vaadin.example.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.components.ResetPasswordComponent;
import org.vaadin.example.services.UserService;

@Route(value = "user/resetPassword", layout = MainLayout.class)
@PageTitle("Reset password")
@AnonymousAllowed
public class ResetPasswordView extends VerticalLayout {

    private UserService userService;

    @Autowired
    public ResetPasswordView(UserService userService){
        this.userService = userService;
        add(new ResetPasswordComponent(userService));
    }
}
