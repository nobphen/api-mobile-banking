package com.phen.mbanking.features.account;

import com.phen.mbanking.domain.Account;
import com.phen.mbanking.domain.AccountType;
import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import com.phen.mbanking.features.accounttype.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;

    @Override
    public AccountResponse createNewAccount(AccountCreateRequest accountCreateRequest) {

        /// Validate account type
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountType()).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account type has not been found"
                )
        );

        /// Transfer DTO to domain model
        Account account = new Account();

        account.setAccountNo(accountCreateRequest.accountNo());
        account.setBalance(accountCreateRequest.balance());
        account.setAccountType(accountType);


        return null;
    }

    @Override
    public List<AccountResponse> findList() {
        return List.of();
    }

    @Override
    public AccountResponse findByActNo(String actNo) {
        return null;
    }
}
