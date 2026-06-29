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
        validateAlias(accountTypeRequest.alias());


        // Validate name
        validateName(accountTypeRequest.name());

        // Transfer DTO to domain model
        AccountType accountType = accountTypeMapper.fromAccountTypeRequest(accountTypeRequest);


        // System generate data
        accountType.setIsDeleted(false);

        accountType = accountTypeRepository.save(accountType);

        return accountTypeMapper.toAccountTypeResponse(accountType);
    }

    @Override
    public List<AccountTypeResponse> findAll() {
       return  null;
    }

    @Override
    public AccountTypeResponse findByName(String name) {
      return  null;
    }

    @Override
    public AccountTypeResponse updateAccountType(Integer id, AccountTypeRequest accountTypeRequest) {



    }

    @Override
    public void delectAccountType(Integer id) {

    }


    /**
     * Validate alias
     *
     * @param alias if of account type
     */
    private void validateAlias(String alias) {
        if (accountTypeRepository.existsByAliasAndIsDeletedFalse(alias)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Alias already exists.");
        }
    }


    private void validateName(String name) {
        if (accountTypeRepository.existsByNameAndIsDeletedFalse(name)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already exists.");
        }
    }
}
