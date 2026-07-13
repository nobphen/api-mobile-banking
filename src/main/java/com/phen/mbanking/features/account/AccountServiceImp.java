package com.phen.mbanking.features.account;

import com.phen.mbanking.domain.Account;
import com.phen.mbanking.domain.AccountType;
import com.phen.mbanking.domain.User;
import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountRenameRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import com.phen.mbanking.features.account.dto.AccountTransferLimitRequest;
import com.phen.mbanking.features.accounttype.AccountTypeRepository;
import com.phen.mbanking.features.user.UserRepository;
import com.phen.mbanking.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    private final AccountMapper accountMapper;


    /**
     * Create account
     *
     * @param accountCreateRequest {@link AccountCreateRequest}
     * @return {@link  AccountResponse}
     */
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
        if (accountRepository.existsByAccountNoAndIsHiddenFalse(accountCreateRequest.accountNo())) {
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

        account.setAccountType(accountType);
        account.setUser(user);

        // System generate data
        account.setAccountName(user.getName());
        account.setIsHidden(false);
        account.setIsDeleted(false);
        account.setTransferLimit(BigDecimal.valueOf(1000));


        /// Save account to database and get last data back
        account = accountRepository.save(account);


        // Transfer domain model to DTO
        return accountMapper.toAccountResponse(account);
    }


    /**
     * Fina all account
     *
     * @param pageNumber is current page request from client
     * @param pageSize   is size of record per page from client
     * @return {@link  List<AccountResponse>}
     */
    @Override
    public Page<AccountResponse> findAll(int pageNumber, int pageSize) {

        // Stort data by id
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");


        // Request to jpa
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        Page<Account> accounts = accountRepository.findAll(pageRequest);


        return accounts.map(accountMapper::toAccountResponse);
    }

    /**
     * Fina account by account no
     *
     * @param accountNo of account
     * @return {@link  AccountResponse}
     */
    @Override
    public AccountResponse findByAccountNo(String accountNo) {

        // Validate account no
        Account account = accountRepository.findByAccountNo(accountNo).orElseThrow(

                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found"
                )
        );

        return accountMapper.toAccountResponse(account);
    }


    /**
     * Rename account
     *
     * @param accountNo            of account
     * @param accountRenameRequest {@link  AccountRenameRequest}
     * @return {@link  AccountResponse}
     */
    @Override
    public AccountResponse renameAccount(String accountNo, AccountRenameRequest accountRenameRequest) {

        // Validate account no
        Account account = accountRepository.findByAccountNo(accountNo).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found"
                )
        );

        // Set update alias
        account.setAlias(accountRenameRequest.alias());


        // Save and return back
        account = accountRepository.save(account);


        return accountMapper.toAccountResponse(account);
    }

    /**
     * Hide account
     *
     * @param accountNo of account
     */
    @Override
    public void hideAccount(String accountNo) {

        // validate account no
        Account account = accountRepository.findByAccountNo(accountNo).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found"
                )
        );

        // Set update data
        account.setIsHidden(true);

        // Save data
        accountRepository.save(account);
    }

    @Override
    public void updateTransferLimitAccount(String accountNo, AccountTransferLimitRequest accountTransferLimitRequest) {
        // validate account no
        Account account = accountRepository.findByAccountNo(accountNo).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found"
                )
        );

        // Set update
        account.setTransferLimit(accountTransferLimitRequest.amount());

        // Save
        accountRepository.save(account);
    }

    /**
     * Delete account
     * @param accountNo of account
     */
    @Override
    public void delectAccount(String accountNo) {
        // validate account no
        Account account = accountRepository.findByAccountNo(accountNo).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found"
                )
        );

        // Set update data
        account.setIsDeleted(true);

        // Save data
        accountRepository.save(account);
    }


}
