package com.phen.mbanking.features.accounttype;

import com.phen.mbanking.features.accounttype.dto.AccountTypeRequest;
import com.phen.mbanking.features.accounttype.dto.AccountTypeResponse;
import com.phen.mbanking.features.accounttype.dto.AccountTypeUpdateRequest;

import java.util.List;

public interface AccountTypeService {

    /**
     * Create new account type
     *
     * @param accountTypeRequest {@link AccountTypeRequest}
     * @return {@link AccountTypeResponse}
     */
    AccountTypeResponse creatAccountType(AccountTypeRequest accountTypeRequest);


    /**
     * Find all account type
     *
     * @return {@link List<AccountTypeResponse>}
     */
    List<AccountTypeResponse> findAll();


    /**
     * Fina by account type name
     *
     * @param name if name of account type
     * @return {@link AccountTypeResponse}
     */
    AccountTypeResponse findByName(String name);


    /**
     *
     * @param alias  of account type
     * @param accountTypeUpdateRequest {@link AccountTypeUpdateRequest}
     * @return {@link  AccountTypeResponse}
     */
    AccountTypeResponse updateAccountTypeByAlias (String alias , AccountTypeUpdateRequest accountTypeUpdateRequest);


    /**
     * Delete account type
     * @param alias of account type
     */
    void  delectAccountTypeByAlias(String alias);

}
