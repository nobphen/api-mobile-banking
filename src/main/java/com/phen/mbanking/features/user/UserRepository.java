package com.phen.mbanking.features.user;

import com.phen.mbanking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Integer> {


    /**
     * Find phone number.
     * SQL:
     * SELECT * FROM tb_user
     * WHERE phoneNumber = ?
     * AND isDeleted = false ;
     */
    Optional<User> findByPhoneNumberAndIsDeletedFalse (String phoneNumber);



    /**
     * Find uuid.
     * SQL:
     * SELECT * FROM tb_user
     * WHERE uuid = ? ;
     */

    Optional<User> findByUuid(String uuid);
}
