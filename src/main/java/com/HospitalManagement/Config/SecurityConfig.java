// File Created by Arun on Monday (09/02/26)

// Importing the necessary packages
package com.HospitalManagement.Config;
import com.HospitalManagement.Filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Creation of Security Config Class
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Creation of instance of AuthFilter
    private final AuthFilter _authFilter;

    // Creation of constructor
    public SecurityConfig(AuthFilter authFilter) {
        this._authFilter = authFilter;
    }

    // Initial testing with basic setup
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Configuring the 'http'
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/auth/test").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(_authFilter , UsernamePasswordAuthenticationFilter.class);

        // Building the 'http'
        return http.build();
    }
}
