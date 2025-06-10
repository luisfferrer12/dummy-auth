package com.telefonica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
        int id,
        String username,
        String email,
        @JsonProperty("token") String accessToken,
        @JsonProperty("refreshToken") String refreshToken
) {

}