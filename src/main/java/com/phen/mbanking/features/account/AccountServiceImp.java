package com.phen.mbanking.features.account;

import com.phen.mbanking.domain.Account;
import com.phen.mbanking.domain.AccountType;
import com.phen.mbanking.domain.User;
import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import com.phen.mbanking.features.accounttype.AccountTypeRepository;
import com.phen.mbanking.features.user.UserRepository;
import com.phen.mbanking.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;

    private  final AccountMapper accountMapper;

    @Override
    public AccountResponse createNewAccount(AccountCreateRequest accountCreateRequest) {

        // Validate account type
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias()).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account type has not been found"
                )
        );


        // Validate user
        User user = userRepository.findByUuid(accountCreateRequest.userUuid()).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User has not been found"
                )
        );

        // Validate account no
        if (accountRepository.existsByAccountNo(accountCreateRequest.accountNo())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Account no has already been existed"
            );
        }


        // Validate balance
        if (accountCreateRequest.balance().compareTo(BigDecimal.valueOf(10)) < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Balance 10$ is required to create account"
            );
        }


        // Transfer DTO to domain model
        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);

        account.setAccountNo(accountCreateRequest.accountNo());
        account.setBalance(accountCreateRequest.balance());
        account.setAccountType(accountType);
        account.setUser(user);

        // System generate data
        account.setAccountName(user.getName());
        account.setIsHidden(false);
        account.setTransferLimit(BigDecimal.valueOf(1000));


        /// Save account to database and get last data back
        account = accountRepository.save(account);


        // Transfer domain model to DTO
        return accountMapper.toAccountResponse(account);
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
