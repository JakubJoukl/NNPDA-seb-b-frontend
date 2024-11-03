package org.vaadin.example.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Route("user/register")
@PageTitle("Register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {

    public RegisterView(){
        add(new H1("Zde bude registrace"));
    }
}
