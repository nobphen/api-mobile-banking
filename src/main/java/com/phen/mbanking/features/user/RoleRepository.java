package com.phen.mbanking.features.user;

import com.phen.mbanking.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Integer> {

   /// JPQL : Jakarta Persistence Query Language
   @Query("""
    SELECT r
    FROM Role r
    WHERE r.name = 'USER'
    """)
   Role findRoleUser();


   @Query("""
    SELECT r
    FROM Role r
    WHERE r.name = 'CUSTOMER'
    """)
   Role findRoleCustomer();

}
