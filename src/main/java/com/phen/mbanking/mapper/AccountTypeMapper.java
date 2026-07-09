package com.phen.mbanking.mapper;

import com.phen.mbanking.domain.AccountType;
import com.phen.mbanking.features.accounttype.dto.AccountTypeRequest;
import com.phen.mbanking.features.accounttype.dto.AccountTypeResponse;
import com.phen.mbanking.features.accounttype.dto.AccountTypeUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

    // partially map
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromAccountTypeUpdateRequest(AccountTypeUpdateRequest accountTypeUpdateRequest, @MappingTarget AccountType accountType);

    // Map account type to account type update request
    AccountType fromAccountTypeUpdateRequest(AccountTypeUpdateRequest accountTypeUpdateRequest);


    // Map account type list to account type response list
    List<AccountTypeResponse> toAccountTypeResponseList(List<AccountType> accountTypes);


    // Map account type to account type response

    AccountTypeResponse toAccountTypeResponse(AccountType accountType);


    // Map from account type to request
    AccountType fromAccountTypeRequest(AccountTypeRequest accountTypeRequest);
}
