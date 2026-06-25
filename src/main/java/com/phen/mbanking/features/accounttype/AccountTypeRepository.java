package com.phen.mbanking.features.accounttype;

import com.phen.mbanking.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

    /// SELECT * FROM  tb_account_type WHARE alias = ?
    Optional<AccountType> findByAlias(String alias);
}
