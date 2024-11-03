package org.vaadin.example.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.context.annotation.Scope;

@Route(value = "user/resetPassword", layout = MainLayout.class)
@PageTitle("Reset password")
@AnonymousAllowed
//Zde dat i nastaveni noveho hesla
public class ResetPasswordView extends VerticalLayout {

}
