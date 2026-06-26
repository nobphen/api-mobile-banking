package com.phen.mbanking.features.user;

import com.phen.mbanking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Integer> {

    // SELECT *FROM tb_user WHARE uuid = ?
    Optional<User> findByUuid(String uuid);
}
