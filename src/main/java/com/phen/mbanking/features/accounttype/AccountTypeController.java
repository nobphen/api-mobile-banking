package com.phen.mbanking.features.accounttype;


import com.phen.mbanking.features.account.dto.AccountResponse;
import com.phen.mbanking.features.accounttype.dto.AccountTypeRequest;
import com.phen.mbanking.features.accounttype.dto.AccountTypeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accountTypes")

@RequiredArgsConstructor
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    /**
     * Create new account type
     *
     * @param accountTypeRequest {@link AccountTypeRequest}
     * @return {@link AccountTypeResponse}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AccountTypeResponse createAccountType(@Valid @RequestBody AccountTypeRequest accountTypeRequest) {
        return accountTypeService.creatAccountType(accountTypeRequest);
    }


    /**
     * Find all account type
     *
     * @return {@link  List<AccountTypeResponse>}
     */
    @GetMapping
    List<AccountTypeResponse> findAll() {
        return accountTypeService.findAll();
    }


    /**
     * Find account type by name
     *
     * @param name of account type
     * @return {@link AccountTypeResponse }
     */
    @GetMapping("/{name}")
    AccountTypeResponse findByName(@PathVariable String name) {
        return accountTypeService.findByName(name);
    }


    /**
     * Update account type
     *
     * @param id                 of account type id
     * @param accountTypeRequest {@link AccountTypeRequest}
     * @return {@link AccountTypeResponse}
     */
    @PutMapping("/{id}")
    AccountTypeResponse updateAccountType(@Valid @PathVariable Integer id, AccountTypeRequest accountTypeRequest) {
        return accountTypeService.updateAccountType(id, accountTypeRequest);
    }


    /**
     * Delete Account Type (Soft Delete)
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountType(@PathVariable Integer id) {
        accountTypeService.delectAccountType(id);
    }

}
