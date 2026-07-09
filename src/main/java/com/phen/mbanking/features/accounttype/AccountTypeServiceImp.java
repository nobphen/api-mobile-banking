package com.phen.mbanking.features.accounttype;

import com.phen.mbanking.domain.AccountType;
import com.phen.mbanking.features.accounttype.dto.AccountTypeRequest;
import com.phen.mbanking.features.accounttype.dto.AccountTypeResponse;
import com.phen.mbanking.features.accounttype.dto.AccountTypeUpdateRequest;
import com.phen.mbanking.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountTypeServiceImp implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;


    /**
     * Create account type
     *
     * @param accountTypeRequest {@link AccountTypeRequest}
     * @return {@link AccountTypeResponse}
     */
    @Override
    public AccountTypeResponse creatAccountType(AccountTypeRequest accountTypeRequest) {


        // Validate alias
        validateAlias(accountTypeRequest.alias());


        // Transfer DTO to domain model
        AccountType accountType = accountTypeMapper.fromAccountTypeRequest(accountTypeRequest);


        // System generate data
        accountType.setIsDeleted(false);

        accountType = accountTypeRepository.save(accountType);

        return accountTypeMapper.toAccountTypeResponse(accountType);
    }

    /**
     * Find all account type
     *
     * @return {@link List<AccountTypeResponse>}
     */
    @Override
    public List<AccountTypeResponse> findAll() {

        Sort sortById = Sort.by(Sort.Direction.DESC, "id");

        List<AccountType> accountTypes = accountTypeRepository.findAll(sortById);

        return accountTypeMapper.toAccountTypeResponseList(accountTypes);
    }

    /**
     * Fina account type
     *
     * @param name if name of account type
     * @return {@link  AccountTypeResponse}
     */
    @Override
    public AccountTypeResponse findByName(String name) {

        AccountType accountType = accountTypeRepository.findByNameAndIsDeletedFalse(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Type not found."));


        return accountTypeMapper.toAccountTypeResponse(accountType);
    }

    /**
     * Update account typw
     * @param alias  of account type
     * @param accountTypeUpdateRequest {@link AccountTypeUpdateRequest}
     * @return {@link AccountTypeResponse}
     */
    @Override
    public AccountTypeResponse updateAccountTypeByAlias(String alias, AccountTypeUpdateRequest accountTypeUpdateRequest) {

        AccountType accountType = accountTypeRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Type alias has not been found."));


        log.info("Before map:{},{},{}", accountType.getId(), accountType.getDescription(), accountType.getIsDeleted());

        accountTypeMapper.fromAccountTypeUpdateRequest(accountTypeUpdateRequest, accountType);

        log.info("After map:{},{},{}", accountType.getId(), accountType.getDescription(), accountType.getIsDeleted());

        /// Save data and get back data
        accountType = accountTypeRepository.save(accountType);

        return accountTypeMapper.toAccountTypeResponse(accountType);
    }

    /**
     * Delete account type
     * @param alias of account type
     */
    @Override
    public void delectAccountTypeByAlias(String alias) {

        AccountType accountType = accountTypeRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Type alias not been found."));


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
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account type alias already exists.");
        }
    }

}
