package com.phen.mbanking.features.user;

import com.phen.mbanking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    /**
     * Find by national card id
     * SQL :
     * SELECT EXISTS
     * SELECT * FORM tb_user
     * WHERE national_card_id = ?
     */
    Boolean existsByNationalCardId(String nationalCardId);

    /**
     * Find by email
     * SQL :
     *  SELECT EXISTS
     * SELECT * FORM tb_user
     * WHERE email = ?
     */

    Boolean existsByEmail(String email);


    /**
     * Find by phone number
     * SQL :
     *  SELECT EXISTS
     * SELECT * FORM tb_user
     * WHERE phone_number = ?
     */
    Boolean existsByPhoneNumber(String phoneNumber);


    /**
     * Find phone number.
     * SQL:
     * SELECT * FROM tb_user
     * WHERE phoneNumber = ?
     * AND isDeleted = false ;
     */
    Optional<User> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);


    /**
     * Find uuid.
     * SQL:
     * SELECT * FROM tb_user
     * WHERE uuid = ? ;
     */

    Optional<User> findByUuid(String uuid);
}
