package com.phen.mbanking.features.account.dto;

import com.phen.mbanking.features.accounttype.dto.AccountTypeResponse;
import lombok.Builder;

import java.math.BigDecimal;


@Builder
public record AccountResponse(
        String alias,
        String accountName,
        String accountNo,
        BigDecimal balance,
        AccountTypeResponse accountType
) {
}
