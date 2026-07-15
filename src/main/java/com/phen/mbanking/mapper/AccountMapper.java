package com.phen.mbanking.mapper;

import com.phen.mbanking.domain.Account;
import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import com.phen.mbanking.features.account.dto.AccountUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // Partially map
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromAccountUpdateRequest(AccountUpdateRequest accountUpdateRequest, @MappingTarget Account account);

    /// Map account to AccountResponse

    //@Mapping(source = "accountType.alias",target = "accountTypeAlias")
    AccountResponse toAccountResponse(Account account);


    /// Map from account request
    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);
}
