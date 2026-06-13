package com.phen.mbanking.features.account;

import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;

public interface AccountService {

    /// crate new account
    AccountResponse createNewAccount(AccountCreateRequest accountCreateRequest);

}
