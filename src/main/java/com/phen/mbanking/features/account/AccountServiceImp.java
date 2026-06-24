package com.phen.mbanking.features.account;

import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImp implements AccountService {

    @Override
    public AccountResponse createNewAccount(AccountCreateRequest accountCreateRequest) {
        return null;
    }
}
