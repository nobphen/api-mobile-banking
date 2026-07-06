package com.phen.mbanking.features.accounttype.dto;

import lombok.Builder;

@Builder
public record AccountTypeResponse(
        String alias,
        String name,
        String description,
        boolean isDeleted
) {
}
