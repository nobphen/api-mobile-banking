package com.phen.mbanking.features.accounttype;

import com.phen.mbanking.domain.AccountType;
import com.phen.mbanking.features.accounttype.dto.AccountTypeRequest;
import com.phen.mbanking.features.accounttype.dto.AccountTypeResponse;
import com.phen.mbanking.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return accountTypeRepository.findAllByIsDeletedFalse(sort).stream().map(accountTypeMapper::toAccountTypeResponse).toList();
    }

    @Override
    public AccountTypeResponse findByName(String name) {

        AccountType accountType = accountTypeRepository.findByNameAndIsDeletedFalse(name).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account Type not found."
                )
        );


        return accountTypeMapper.toAccountTypeResponse(accountType);
    }

    @Override
    public AccountTypeResponse updateAccountType(Integer id, AccountTypeRequest accountTypeRequest) {

        AccountType accountType = accountTypeRepository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account Type id not found."
                )
        );

        // Validate alias
        if (!accountType.getAlias().equals(accountTypeRequest.alias())) {
            validateAlias(accountTypeRequest.alias());
        }

        // Validate name
        if (!accountType.getName().equals(accountTypeRequest.name())) {
            validateName(accountTypeRequest.name());
        }


        accountType.setAlias(accountTypeRequest.alias());
        accountType.setName(accountTypeRequest.name());
        accountType.setDescription(accountTypeRequest.description());

        /// Save data and get back data
        accountType = accountTypeRepository.save(accountType);


        return accountTypeMapper.toAccountTypeResponse(accountType);
    }

    @Override
    public void delectAccountType(Integer id) {

        AccountType accountType = accountTypeRepository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account Type id not found."
                )
        );


        // Set update data
        accountType.setIsDeleted(true);

        accountTypeRepository.save(accountType);

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
