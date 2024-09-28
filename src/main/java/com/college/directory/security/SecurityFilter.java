package com.college.directory.security;

import com.college.directory.config.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManageConfig ->
                        sessionManageConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {

                    authConfig
                            .requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll()
                            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                            .requestMatchers("/error").permitAll();

                    authConfig
                            .requestMatchers(HttpMethod.GET, "/users").hasAuthority("ROLE_ADMINISTRATOR")
                            .requestMatchers(HttpMethod.POST, "/users").hasAuthority("ROLE_ADMINISTRATOR")
                            .requestMatchers(HttpMethod.GET, "/courses").hasAuthority("ROLE_FACULTY_MEMBER")
                            .requestMatchers(HttpMethod.GET, "/enrollments").hasAuthority("ROLE_STUDENT")
                            .anyRequest().authenticated();
                });

        return http.build();
    }
}
