package com.phen.mbanking.features.account;

import com.phen.mbanking.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    // SELECT EXISTS(SELECT *FROM tb_accounts WHERE accountNo = ?)
    boolean existsByAccountNo(String actNo);
}
