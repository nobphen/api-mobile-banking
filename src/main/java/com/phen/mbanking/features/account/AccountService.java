package com.phen.mbanking.features.account;

import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountRenameRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import com.phen.mbanking.features.account.dto.AccountTransferLimitRequest;
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
     *
     * @param pageNumber is current page request from client
     * @param pageSize   is size of record per page from client
     * @return {@link List<AccountResponse>}
     */
    Page<AccountResponse> findAll(int pageNumber, int pageSize);

    /**
     *
     * @param actNo is no of account
     * @return {@link AccountResponse}
     */
    AccountResponse findByAccountNo(String findByAccountNo);


    /**
     * Rename account
     *
     * @param accountNo            of account
     * @param accountRenameRequest {@link  AccountRenameRequest}
     * @return {@link  AccountResponse}
     */
    AccountResponse renameAccount(String accountNo, AccountRenameRequest accountRenameRequest);


    /**
     * Hide account
     *
     * @param accountNo of account
     */
    void hideAccount(String accountNo);


    /**
     * Account transfer
     *
     * @param accountNo                   of account
     * @param accountTransferLimitRequest {@link AccountTransferLimitRequest}
     */
    void updateTransferLimitAccount(String accountNo, AccountTransferLimitRequest accountTransferLimitRequest);


    /**
     * Delete account
     * @param accountNo of account
     */
    void  delectAccount(String accountNo);

}
