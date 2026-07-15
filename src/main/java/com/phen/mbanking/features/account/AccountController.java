package com.phen.mbanking.features.account;


import com.phen.mbanking.features.account.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
     * @param pageNumber is current page request from client
     * @param pageSize   is size record per page from client
     * @return {@link List<AccountResponse>}
     */
    @GetMapping
    Page<AccountResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "15") int pageSize
    ) {
        return accountService.findAll(pageNumber, pageSize);
    }

    /**
     * Find by account no
     *
     * @param accountNo of account
     * @return {@link AccountResponse}
     */
    @GetMapping("/{accountNo}")
    AccountResponse findByAccountNo(@PathVariable("accountNo") String accountNo) {
        return accountService.findByAccountNo(accountNo);
    }


    /**
     * Rename account
     *
     * @param accountNo of account
     * @return {@link AccountResponse}
     */

    @PutMapping("/{accountNo}/rename")
    AccountResponse renameAccount(@PathVariable("accountNo") String accountNo, @Valid @RequestBody AccountRenameRequest accountRenameRequest) {

        return accountService.renameAccount(accountNo, accountRenameRequest);
    }

    /**
     * Hide account
     *
     * @param accountNo of account
     */

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{accountNo}/hide-account")
    void hideAccount(@PathVariable("accountNo") String accountNo) {
        accountService.hideAccount(accountNo);
    }

    /**
     * Hide account
     *
     * @param accountNo of account
     */

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{accountNo}/transfer-limit")
    void updateTransferLimitAccount(
            @PathVariable("accountNo") String accountNo,
            @Valid @RequestBody AccountTransferLimitRequest accountTransferLimitRequest
    ) {
        accountService.updateTransferLimitAccount(accountNo, accountTransferLimitRequest);
    }


    @PatchMapping("/{alias}")
    AccountResponse updateAccountByAlias(@PathVariable String alias, @RequestBody AccountUpdateRequest accountUpdateRequest){
        return  accountService.updateAccountByAlias(alias,accountUpdateRequest);
    }


    /**
     * Delete account
     * @param accountNo of account
     */
    @DeleteMapping("/{accountNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delectAccount(@PathVariable String accountNo) {
        accountService.delectAccount(accountNo);
    }
}
