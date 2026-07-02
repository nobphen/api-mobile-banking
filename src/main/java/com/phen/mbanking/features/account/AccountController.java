package com.phen.mbanking.features.account;


import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")

@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    /**
     * Create new account
     *
     * @param accountCreateRequest {@link  AccountCreateRequest}
     * @return {@link AccountResponse}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AccountResponse createNewAccount(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        return accountService.createNewAccount(accountCreateRequest);
    }


    /**
     * Find all account
     *
     * @return {@link List<AccountResponse>}
     */
    @GetMapping
    List<AccountResponse> findAll() {
        return accountService.findAll();
    }

    /**
     * Find by account no
     *
     * @param accountNo of account
     * @return {@link AccountResponse}
     */
    @GetMapping("/{accountNo}")
    AccountResponse findByAccountNo(@PathVariable String accountNo) {
        return accountService.findByAccountNo(accountNo);
    }
}
