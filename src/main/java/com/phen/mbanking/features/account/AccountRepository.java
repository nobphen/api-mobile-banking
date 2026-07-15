package com.phen.mbanking.features.account;

import com.phen.mbanking.domain.Account;
import com.phen.mbanking.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Find account no.
     * SQL:
     * SELECT * FROM tb_accounts
     * WHERE actNo = ? Anc isHidden = false;
     */
    boolean existsByAccountNoAndIsHiddenFalse(String actNo);


    /**
     * Find account no.
     * SQL:
     * SELECT * FROM tb_accounts
     * WHERE accountNo = ?
     */
    Optional<Account> findByAccountNo(String accountNo);


    /**
     * Find alias
     * SQL:
     * SELECT * FROM tb_accounts
     * WHERE alias = ?
     * AND isDelete = false
     */
    Optional<Account> findByAliasAndIsDeletedFalse(String alias);
}
