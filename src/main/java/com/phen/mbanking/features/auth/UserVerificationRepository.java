package com.phen.mbanking.features.auth;

import com.phen.mbanking.domain.User;
import com.phen.mbanking.domain.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserVerificationRepository extends JpaRepository<UserVerification,Long> {


    Optional<UserVerification> findByUser(User user);

    /**
     * Find verified code .
     * SQL:
     * SELECT * FROM tb_user_verification
     * WHERE verifiedCode = ?
     */
    Optional<UserVerification> findByUserAndVerifiedCode(User user,String verifiedCode);
}
