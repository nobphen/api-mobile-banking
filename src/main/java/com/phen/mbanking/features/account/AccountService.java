package com.phen.mbanking.features.account;

import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;

import java.util.List;

public interface AccountService {

    /**
     * Create a new account
     *
     * @param accountCreateRequest {@link AccountCreateRequest}
     * @return {@link AccountResponse}
     */
    AccountResponse createNewAccount(AccountCreateRequest accountCreateRequest);


    /**
     * Find all account
     *
     * @return {@link List<AccountResponse>}
     */
    List<AccountResponse> findList();

    /**
     *
     * @param actNo is no of account
     * @return {@link AccountResponse}
     */
    AccountResponse findByActNo(String actNo);
}
