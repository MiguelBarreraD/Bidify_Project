package edu.ieti.bidify.security.dto;

public class JWTTokenDto {
    private String token;

    public JWTTokenDto() {
    }

    public JWTTokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
