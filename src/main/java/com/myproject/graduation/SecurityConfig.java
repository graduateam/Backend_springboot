package com.myproject.graduation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/users/**",
                                "/swagger-ui/**",
                                "/api-docs/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()); // CSRF 비활성화 (테스트 및 API 용이)
        return http.build();
    }
}