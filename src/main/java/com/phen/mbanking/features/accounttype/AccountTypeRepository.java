package com.phen.mbanking.features.accounttype;

import com.phen.mbanking.domain.AccountType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

    /**
     * Find account type by alias.
     * SQL:
     * SELECT * FROM tb_account_types
     * WHERE alias = ? AND is_deleted = false;
     */
    Optional<AccountType> findByAliasAndIsDeletedFalse(String alias);

    /**
     * Find account type by name.
     * SQL:
     * SELECT * FROM tb_account_types
     * WHERE name = ? AND is_deleted = false;
     */
    Optional<AccountType> findByNameAndIsDeletedFalse(String name);

    /**
     * Check alias already exists.
     * SQL:
     * SELECT EXISTS(
     *      SELECT 1
     *      FROM tb_account_types
     *      WHERE alias = ?
     *      AND is_deleted = false
     * );
     */
    boolean existsByAliasAndIsDeletedFalse(String alias);

    /**
     * Check name already exists.
     * SQL:
     * SELECT EXISTS(
     *      SELECT 1
     *      FROM tb_account_types
     *      WHERE name = ?
     *      AND is_deleted = false
     * );
     */
    boolean existsByNameAndIsDeletedFalse(String name);

    /**
     * Find by id.
     * SQL:
     * SELECT *
     * FROM tb_account_types
     * WHERE id = ?
     * AND is_deleted = false;
     */
    Optional<AccountType> findByIdAndIsDeletedFalse(Integer id);

    /**
     * Find all active account types.
     * SQL:
     * SELECT *
     * FROM tb_account_types
     * WHERE is_deleted = false;
     */
    List<AccountType> findAllByIsDeletedFalse(Sort sort);

}
