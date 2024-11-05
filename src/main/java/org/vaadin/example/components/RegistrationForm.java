package org.vaadin.example.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.example.dtos.user.RegistrationDto;
import org.vaadin.example.services.UserService;

public class RegistrationForm extends VerticalLayout {

    private UserService userService;
    private TextField usernameField = new TextField("Username");
    private EmailField emailField = new EmailField("Email");
    private PasswordField passwordField = new PasswordField("Password");
    private Button register = new Button("Register");

    private FormLayout formLayout = new FormLayout();

    public RegistrationForm(UserService userService){
        this.userService = userService;
        setSizeFull();

        formLayout.setSizeFull();
        formLayout.add(emailField, usernameField, passwordField);
        register.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        register.addClickListener(e -> registerUser());

        add(formLayout, register);
    }

    private void registerUser() {
        String username = usernameField.getValue();
        String email = emailField.getValue();
        String password = passwordField.getValue();

        RegistrationDto registrationDto = new RegistrationDto(username, password, email);
        userService.register(registrationDto);
    }
}
