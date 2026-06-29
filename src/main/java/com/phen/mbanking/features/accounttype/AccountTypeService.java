package com.phen.mbanking.features.accounttype;

import com.phen.mbanking.features.accounttype.dto.AccountTypeRequest;
import com.phen.mbanking.features.accounttype.dto.AccountTypeResponse;

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
     * @param id if id of account type
     * @param accountTypeRequest {@link AccountTypeRequest}
     * @return {@link  AccountTypeResponse}
     */
    AccountTypeResponse updateAccountType (Integer id ,AccountTypeRequest accountTypeRequest);


    void  delectAccountType(Integer id);

}
