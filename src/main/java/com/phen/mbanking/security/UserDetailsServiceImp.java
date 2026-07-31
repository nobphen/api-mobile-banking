package com.phen.mbanking.security;

import com.phen.mbanking.domain.User;
import com.phen.mbanking.features.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Setter
@Getter
@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String phoneNumber) throws UsernameNotFoundException {

        User user = userRepository.findByPhoneNumberAndIsDeletedFalse(phoneNumber).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User not been found"
                )
        );

        log.info("log user {}" ,user.getPhoneNumber());

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);


        return customUserDetails;
    }
}
