package org.vaadin.example.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.example.dtos.user.NewPasswordDto;
import org.vaadin.example.dtos.user.ResetPasswordDto;
import org.vaadin.example.services.UserService;

public class ResetPasswordComponent extends VerticalLayout {

    private UserService userService;
    private TextField usernameField = new TextField("Username");

    private PasswordField newPasswordField = new PasswordField("New password");
    private TextField tokenField = new TextField("Token");

    FormLayout resetPasswordFormLayout = new FormLayout();

    public ResetPasswordComponent(UserService userService){
        this.userService = userService;
        setSizeFull();

        FlexLayout resetPasswordLayout = getResetPasswordLayout();
        FlexLayout newPasswordLayout = getNewPasswordLayout();

        add(resetPasswordLayout, newPasswordLayout);
    }

    private FlexLayout getNewPasswordLayout() {
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        flexLayout.setWidth("100%");

        Button resetPasswordButton = new Button("Reset password");
        resetPasswordButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        resetPasswordButton.addClickListener(e -> resetPassword());

        resetPasswordFormLayout.add(newPasswordField, tokenField);

        flexLayout.add(resetPasswordFormLayout, resetPasswordButton);
        return flexLayout;
    }

    private void resetPassword() {
        String password = newPasswordField.getValue();
        String token = tokenField.getValue();
        NewPasswordDto newPasswordDto = new NewPasswordDto(token, password);

        userService.newPassword(newPasswordDto);
    }

    private FlexLayout getResetPasswordLayout() {
        FlexLayout resetPasswordLayout = new FlexLayout();
        resetPasswordLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        resetPasswordLayout.setWidth("100%");

        usernameField.setWidth("100%");
        Button requestToResetPasswordButton = new Button("Request to reset password");
        requestToResetPasswordButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        requestToResetPasswordButton.addClickListener(e -> requestToResetPassword());

        resetPasswordLayout.add(usernameField, requestToResetPasswordButton);
        return resetPasswordLayout;
    }

    private void requestToResetPassword() {
        String username = usernameField.getValue();
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto(username);
        userService.requestToResetPassword(resetPasswordDto);
    }
}
