package com.phen.mbanking.features.accounttype.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountTypeUpdateRequest(
        String description,

        Boolean  isDeleted
) {
}
