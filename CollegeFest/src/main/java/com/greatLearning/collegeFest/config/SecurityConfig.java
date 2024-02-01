package com.greatLearning.collegeFest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl jdbcDaoImpl = new org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl();
        jdbcDaoImpl.setDataSource(dataSource);
        return jdbcDaoImpl;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/students").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/students/add").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/students/edit/**").hasRole("ADMIN")
                    .antMatchers("/students/delete/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/student/list")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll()
            )
            .logout(logout -> logout.permitAll())
            .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/access-denied"));

        return http.build();
    }
}
