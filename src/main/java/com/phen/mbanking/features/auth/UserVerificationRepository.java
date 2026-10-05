package com.phen.mbanking.features.auth;

import com.phen.mbanking.domain.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserVerification,Long> {
}
