package org.vaadin.example.components;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import org.vaadin.example.dtos.user.ChangePasswordDto;
import org.vaadin.example.dtos.user.ResetPasswordDto;
import org.vaadin.example.services.UserService;
import org.vaadin.example.views.MeasuringDeviceView;

public class ChangePasswordForm extends VerticalLayout {

    private FormLayout formLayout = new FormLayout();
    private PasswordField oldPasswordField = new PasswordField("Old password");
    private PasswordField newPasswordField = new PasswordField("New password");
    private Button resetPasswordButton = new Button("Reset password");

    private UserService userService;

    public ChangePasswordForm(UserService userService){
        this.userService = userService;
        H1 header = new H1("Change password form");
        setSizeFull();

        formLayout.setSizeFull();
        formLayout.add(oldPasswordField, newPasswordField);
        resetPasswordButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        resetPasswordButton.addClickListener(e -> resetPassword());

        add(header, formLayout, resetPasswordButton);
    }

    private void resetPassword() {
        ChangePasswordDto changePasswordDto = new ChangePasswordDto();
        changePasswordDto.setOldPassword(oldPasswordField.getValue());
        changePasswordDto.setNewPassword(newPasswordField.getValue());
        userService.changePassword(changePasswordDto);
        UI.getCurrent().navigate(MeasuringDeviceView.class);
    }
}
