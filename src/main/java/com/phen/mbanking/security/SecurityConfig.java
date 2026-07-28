package com.phen.mbanking.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;


    @Bean
    InMemoryUserDetailsManager configureUseSecurity() {


        // User admin
        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("USER", "ADMIN")
                .build();


        /// User editor
        UserDetails editor = User
                .withUsername("editor")
                .password(passwordEncoder.encode("editor123"))
                .roles("USER", "EDITOR")
                .build();

        /// User subscriber
        UserDetails subscriber = User
                .withUsername("subscriber")
                .password(passwordEncoder.encode("subscriber123"))
                .roles("USER", "'SUBSCRIBER'")
                .build();

        /// User customer
        UserDetails customer = User
                .withUsername("customer")
                .password(passwordEncoder.encode("customer123"))
                .roles("USER", "CUSTOMER")
                .build();

        /// User customer
        UserDetails managers = User
                .withUsername("manager")
                .password(passwordEncoder.encode("manager123"))
                .roles("USER", "MANAGER")
                .build();

        /// Manger memory
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(admin);
        manager.createUser(editor);
        manager.createUser(subscriber);
        manager.createUser(customer);
        manager.createUser(managers);

        return manager;


    }

    @Bean
    SecurityFilterChain configureApiSecurity(HttpSecurity http) {

        /// Endpoint Security Config
        http.authorizeHttpRequests(endpoint -> endpoint
                .requestMatchers(HttpMethod.POST, "/v1/api/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/v1/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/v1/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/accounts/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/account-types/**").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/account-types/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/account-types/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/v1/account-types/**").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/account-types/**").hasAnyRole("MANAGER", "ADMIN")
                .anyRequest()
                .authenticated()
        );


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
