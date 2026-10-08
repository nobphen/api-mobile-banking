package com.phen.mbanking.features.auth.dto;

public record AuthResponse(
        String tokenTyp,

        String accessToken,

        String refreshToken
) {
}
