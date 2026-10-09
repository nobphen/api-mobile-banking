package com.phen.mbanking.features.auth.dto;

import lombok.Builder;

@Builder
public record AuthResponse(
        String tokenTyp,

        String accessToken,

        String refreshToken
) {
}
