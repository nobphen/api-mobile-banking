package com.phen.mbanking.init;


import com.phen.mbanking.domain.AccountType;
import com.phen.mbanking.domain.Role;
import com.phen.mbanking.domain.User;
import com.phen.mbanking.features.accounttype.AccountTypeRepository;
import com.phen.mbanking.features.user.RoleRepository;
import com.phen.mbanking.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final RoleRepository roleRepository;


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
            user.setName("USRE");


            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role  manager = new Role();
            manager.setName("MANAGER");

            Role   admin = new Role();
            admin.setName("ADMIN");

            roleRepository.saveAll(List.of(user,customer,manager,admin));


            User user1 = new User();

            user1.setUuid(UUID.randomUUID().toString());
            user1.setName("Nob Phen");
            user1.setGender("Male");
            user1.setPhoneNumber("0969530336");
            user1.setPin("1234");
            user1.setPassword("qwer");
            user1.setNationalCardId("123456789");
            user1.setProfileImage("user/avatar.png");
            user1.setStudentCardId("RUPP-000001");
            user1.setIsDeleted(false);
            user1.setIsBlocked(false);

            User user2 = new User();

            user2.setUuid(UUID.randomUUID().toString());
            user2.setName("Im vannet");
            user2.setGender("Male");
            user2.setPhoneNumber("0969530337");
            user2.setPin("1234");
            user2.setPassword("qwer");
            user2.setNationalCardId("88889999");
            user2.setProfileImage("user/avatar.png");
            user2.setIsDeleted(false);
            user2.setIsBlocked(false);


            //userRepository.save(user);
            //userRepository.save(user2);

            userRepository.saveAll(List.of(user1, user2));
        }

    }

}
