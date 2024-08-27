package com.exam.project.reservation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

@Configuration
public class RestAPISecurityConfig {

    @Value("${spring.data.rest.base-path}")
    private String apiPath;

    // use the roles and users table for security validation
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // define query for usernames
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users where enabled=1 and username=?");
        // define query for authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, role from roles where username=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configure ->
                configure
                        .requestMatchers(HttpMethod.GET, apiPath.concat("/customers")).hasRole("WEBAPI")
                        .requestMatchers(HttpMethod.GET, apiPath.concat("/customers/**")).hasRole("WEBAPI")
                        .requestMatchers(HttpMethod.POST, apiPath.concat("/customers")).hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, apiPath.concat("/customers")).hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, apiPath.concat("/customers/**")).hasRole("ADMIN")
        );

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }


}
