package com.phen.mbanking.features.account.dto;

import lombok.Builder;

import java.math.BigDecimal;


@Builder
public record AccountResponse(
        String alias,
        String accountName,
        String accountNo,
        BigDecimal balance,
        String accountTypeAlias
) {
}
