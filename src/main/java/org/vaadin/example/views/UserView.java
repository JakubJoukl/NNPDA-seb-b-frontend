package org.vaadin.example.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//Zde dat metodu na zmenu hesla
@Route(value = "user/user", layout = MainLayout.class)
@PageTitle("User")
@AnonymousAllowed
@Component
@Scope("prototype")
public class UserView extends VerticalLayout {

}
