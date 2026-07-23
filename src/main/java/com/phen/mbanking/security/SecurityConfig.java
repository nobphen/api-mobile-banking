package com.phen.mbanking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    InMemoryUserDetailsManager configureUseSecurity(){

        // User admin
        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin123")
                .roles("USER","ADMIN")
                .build();


        /// User editor
        UserDetails editor = User
                .withUsername("editor")
                .password("{noop}editor123")
                .roles("USER","EDITOR")
                .build();

        /// User subscriber
        UserDetails subscriber = User
                .withUsername("subscriber")
                .password("{noop}subscriber123")
                .roles("USER","'SUBSCRIBER'")
                .build();

        /// Manger memory
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(admin);
        manager.createUser(editor);
        manager.createUser(subscriber);


        return  manager;


    }

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
