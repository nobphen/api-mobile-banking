package com.phen.mbanking.features.account.dto;

import java.math.BigDecimal;

public record AccountResponse(
        String alias,
        String accountName,
        String accountNo,
        BigDecimal balance,
        String accountType
) {
}
