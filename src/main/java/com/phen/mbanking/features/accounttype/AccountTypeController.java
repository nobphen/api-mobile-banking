package com.phen.mbanking.features.accounttype;


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


}
