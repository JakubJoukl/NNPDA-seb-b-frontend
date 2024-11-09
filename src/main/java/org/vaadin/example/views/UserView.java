package org.vaadin.example.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.example.components.ChangePasswordForm;
import org.vaadin.example.services.UserService;

//Zde dat metodu na zmenu hesla
@Route(value = "user/user", layout = MainLayout.class)
@PageTitle("User")
@AnonymousAllowed
@Component
@Scope("prototype")
public class UserView extends VerticalLayout {
    private UserService userService;

    @Autowired
    private UserView(UserService userService) {
        this.userService = userService;
        add(new ChangePasswordForm(userService));
    }
}
