package com.phen.mbanking.features.account;


import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")

@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AccountResponse createNewAccount(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        return accountService.createNewAccount(accountCreateRequest);
    }
}
