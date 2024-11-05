package org.vaadin.example.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.components.RegistrationForm;
import org.vaadin.example.services.UserService;

@Route(value = "user/register", layout = MainLayout.class)
@PageTitle("Register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {

    private UserService userService;

    @Autowired
    public RegisterView(UserService userService){
        this.userService = userService;
        add(new H1("Registration form"));
        add(new RegistrationForm(userService));
    }
}
