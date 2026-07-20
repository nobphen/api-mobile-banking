package com.phen.mbanking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain configureApiSecurity(HttpSecurity http) {

        /// Endpoint Security Config
        http.authorizeHttpRequests(endpoint -> endpoint.anyRequest().authenticated());


        /// Security Mechanism ( HTTP Basic Auth )
        /// HTTP Basic Auth ( Username & Password)
        http.httpBasic(Customizer.withDefaults());


        /// Disable CSRF Token
        http.csrf(AbstractHttpConfigurer::disable);

        /// Make Stateless Session
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }
}
