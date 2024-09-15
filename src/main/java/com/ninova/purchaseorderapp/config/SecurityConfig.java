package com.ninova.purchaseorderapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ninova.purchaseorderapp.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;  // Your custom JWT filter
    
    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;  // Custom user details service to load users

    // Define the SecurityFilterChain Bean to configure the security aspects like HTTP authorization, authentication, and session management
    @Bean
    public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF since we are stateless with JWT
            .authorizeRequests()
                .requestMatchers("/api/users/register", "/api/auth/login").permitAll()  // Permit public access to login and register endpoints
                .anyRequest().authenticated()  // Secure other endpoints
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless sessions for JWT
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Add the JWT filter before the UsernamePasswordAuthenticationFilter

        return http.build();  // Build the security configuration
    }

    // Define the AuthenticationManager Bean for managing authentication processes
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(customUserDetailsService)  // Use custom user details service
            .passwordEncoder(passwordEncoder())  // Use BCrypt password encoder
            .and()
            .build();
    }

    // Password encoder bean for encrypting user passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt is a good choice for password encoding
    }
}
