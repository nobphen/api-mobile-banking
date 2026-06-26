package com.phen.mbanking.features.accounttype;

import com.phen.mbanking.domain.AccountType;
import com.phen.mbanking.features.accounttype.dto.AccountTypeRequest;
import com.phen.mbanking.features.accounttype.dto.AccountTypeResponse;
import com.phen.mbanking.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImp implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;


    @Override
    public AccountTypeResponse creatAccountType(AccountTypeRequest accountTypeRequest) {

        // Validate alias


        // Transfer DTO to domain model
        AccountType accountType = accountTypeMapper.fromAccountTypeRequest(accountTypeRequest);

        accountType.setAlias(accountTypeRequest.alias());
        accountType.setName(accountTypeRequest.name());
        accountType.setDescription(accountTypeRequest.description());

        // System generate data
        accountType.setIdDeleted(false);

        accountType = accountTypeRepository.save(accountType);

        return accountTypeMapper.toAccountTypeResponse(accountType);
    }

    @Override
    public List<AccountTypeResponse> findList() {
        return List.of();
    }

    @Override
    public AccountTypeResponse findByName(String name) {
        return null;
    }
}
