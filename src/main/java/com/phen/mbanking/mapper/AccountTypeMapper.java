package com.phen.mbanking.mapper;

import com.phen.mbanking.domain.AccountType;
import com.phen.mbanking.features.accounttype.dto.AccountTypeRequest;
import com.phen.mbanking.features.accounttype.dto.AccountTypeResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

    // Map account type to account type response

    AccountTypeResponse toAccountTypeResponse(AccountType accountType);


    // Map from account type to request
    AccountType fromAccountTypeRequest(AccountTypeRequest accountTypeRequest);
}
