package com.phen.mbanking.features.account.dto;

import java.math.BigDecimal;

public record AccountUpdateRequest(

        String accountNo,

        BigDecimal balance,

        Boolean isHidden,

        Boolean isDeleted
) {
}
