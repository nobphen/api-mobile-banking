package com.phen.mbanking.features.account;

import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import org.springframework.data.domain.Page;

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
     * Find all accounts by pagination
     * @param pageNumber is current page request from client
     * @param pageSize is size of record per page from client
     * @return {@link List<AccountResponse>}
     */
    Page<AccountResponse> findAll(int pageNumber, int pageSize);

    /**
     *
     * @param actNo is no of account
     * @return {@link AccountResponse}
     */
    AccountResponse findByAccountNo(String findByAccountNo);
}
