package org.vaadin.example.dtos.user;

public class ResetPasswordDto {
    private String username;

    public ResetPasswordDto(String username) {
        this.username = username;
    }

    public ResetPasswordDto(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
