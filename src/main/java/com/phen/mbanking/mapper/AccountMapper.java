package com.phen.mbanking.mapper;

import com.phen.mbanking.domain.Account;
import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    /// Map account to AccountResponse

    //@Mapping(source = "accountType.alias",target = "accountTypeAlias")
    AccountResponse toAccountResponse(Account account);


    /// Map from account request
    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);
}
