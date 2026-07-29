package com.phen.mbanking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncodeConfig {

    @Bean
    PasswordEncoder configPasswordEncode() {
        return new BCryptPasswordEncoder();
    }
}
