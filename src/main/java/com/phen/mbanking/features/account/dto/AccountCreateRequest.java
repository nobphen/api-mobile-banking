package com.phen.mbanking.features.account.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record AccountCreateRequest(

        @NotBlank(message = "Account no it required")
        String accountNo,

        @NotBlank(message = "Balance is required")
        BigDecimal balance,

        @NotBlank(message = "Account type is required")
        String accountType,

        @NotBlank(message = "Account owner is required")
        String userUuid
) {
}
