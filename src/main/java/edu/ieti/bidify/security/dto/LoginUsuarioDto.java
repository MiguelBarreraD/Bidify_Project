package edu.ieti.bidify.security.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginUsuarioDto {
    
    @NotBlank(message = "Username is mandatory")
    private String userName;
    @NotBlank(message = "Password is mandatory")
    private String password;

    public LoginUsuarioDto() {
    }

    public LoginUsuarioDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
