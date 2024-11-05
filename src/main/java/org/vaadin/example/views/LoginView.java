package org.vaadin.example.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.dtos.user.LoginDto;
import org.vaadin.example.services.UserService;

@Route(value = "user/login", layout = MainLayout.class)
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends FlexLayout {

    //TODO melo byt jako samostatna komponenta...
    private TextField usernameField = new TextField("Username");
    private PasswordField passwordField = new PasswordField("Password");
    private Button loginButton = new Button("Login");

    @Autowired
    private UserService userService;

    public LoginView() {
        this.getStyle().set("padding", "5%");
        this.setFlexDirection(FlexDirection.COLUMN);

        FormLayout formLayout = new FormLayout();
        formLayout.add(usernameField, passwordField, loginButton);
        loginButton.addClickListener(event -> login(usernameField.getValue(), passwordField.getValue()));

        FlexLayout linkLayout = new FlexLayout();
        linkLayout.setFlexDirection(FlexDirection.ROW);
        RouterLink registerLink = new RouterLink("Register", RegisterView.class);
        RouterLink resetPasswordLink = new RouterLink("Reset password", ResetPasswordView.class);
        linkLayout.add(registerLink, resetPasswordLink);

        add(formLayout, linkLayout);
    }

    private void login(String username, String password) {
        LoginDto loginDto = new LoginDto(username, password);
        String token = userService.login(loginDto);
        if(token != null) {
            VaadinSession.getCurrent().setAttribute("jwt", token);
            Notification.show("Přihlášení bylo úspěšné, nice");
            UI.getCurrent().navigate(MeasuringDeviceView.class);
        }
    }
}
