package com.vti.blogapp.configuration;

import com.vti.blogapp.exception.ErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ErrorHandler errorHandler) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(cutomizer -> cutomizer
                        .requestMatchers(HttpMethod.DELETE)
                        .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(errorHandler)
                        .accessDeniedHandler(errorHandler))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
