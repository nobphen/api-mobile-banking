package com.phen.mbanking.init;


import com.phen.mbanking.domain.AccountType;
import com.phen.mbanking.domain.Role;
import com.phen.mbanking.domain.User;
import com.phen.mbanking.features.accounttype.AccountTypeRepository;
import com.phen.mbanking.features.user.RoleRepository;
import com.phen.mbanking.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @PostConstruct
    void init() {


//        if (accountTypeRepository.count() == 0) {
//
//            // Account payroll
//            AccountType payroll = new AccountType();
//
//            payroll.setName("Payroll");
//            payroll.setAlias("payroll");
//            payroll.setIdDeleted(false);
//            payroll.setDescription("Payroll Account of user");
//
//
//            // Account saving
//            AccountType saving = new AccountType();
//
//            saving.setName("Saving");
//            saving.setAlias("saving");
//            saving.setIdDeleted(false);
//            saving.setDescription("Saving Account of user");
//
//
//            accountTypeRepository.saveAll(List.of(payroll, saving));
//        }

        if (userRepository.count() == 0) {

            Role user = new Role();
            user.setName("USER");


            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role manager = new Role();
            manager.setName("MANAGER");

            Role admin = new Role();
            admin.setName("ADMIN");

            roleRepository.saveAll(List.of(user, customer, manager, admin));


            User user1 = new User();

            user1.setUuid(UUID.randomUUID().toString());
            user1.setName("Nob Phen");
            user1.setGender("Male");
            user1.setPhoneNumber("0969530336");
            user1.setPin("1234");
            user1.setPassword(passwordEncoder.encode("qwer"));
            user1.setNationalCardId("123456789");
            user1.setProfileImage("user/avatar.png");
            user1.setStudentCardId("RUPP-000001");
            user1.setIsDeleted(false);
            user1.setIsBlocked(false);
            user1.setRoles(List.of(user, admin));

            User user2 = new User();

            user2.setUuid(UUID.randomUUID().toString());
            user2.setName("Im vannet");
            user2.setGender("Male");
            user2.setPhoneNumber("0969530337");
            user2.setPin("1234");
            user2.setPassword(passwordEncoder.encode("qwer"));
            user2.setNationalCardId("88889990");
            user2.setProfileImage("user/avatar.png");
            user2.setIsDeleted(false);
            user2.setIsBlocked(false);
            user2.setRoles(List.of(user, manager));


            User user3 = new User();

            user3.setUuid(UUID.randomUUID().toString());
            user3.setName("CR7");
            user3.setGender("Male");
            user3.setPhoneNumber("0969530338");
            user3.setPin("7777");
            user3.setPassword(passwordEncoder.encode("qwer"));
            user3.setNationalCardId("88889991");
            user3.setProfileImage("user/avatar.png");
            user3.setIsDeleted(false);
            user3.setIsBlocked(false);
            user3.setRoles(List.of(user, customer));


            //userRepository.save(user);
            //userRepository.save(user2);

            userRepository.saveAll(List.of(user1, user2, user3));
        }

    }

}
