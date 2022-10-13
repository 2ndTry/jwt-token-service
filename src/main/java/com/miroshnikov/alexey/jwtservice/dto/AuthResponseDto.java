package com.miroshnikov.alexey.jwtservice.dto;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String username;
    private String token;
}
