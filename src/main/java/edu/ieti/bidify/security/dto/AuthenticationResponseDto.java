package edu.ieti.bidify.security.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDto {
    private String accesToken;
    private String tokenType = "Bearer ";

    public AuthenticationResponseDto(String accesToken) {
        this.accesToken = accesToken;
    }
}
