package com.phen.mbanking.features.account.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountRenameRequest(
        @NotBlank(message = "Account alias is required")
        String alias
) {
}
