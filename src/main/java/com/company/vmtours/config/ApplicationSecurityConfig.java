package com.company.vmtours.config;

import com.company.vmtours.model.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Handlers handlers;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> jdbcConfigurer = auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema();

        setUsers(jdbcConfigurer);
        setAdmins(jdbcConfigurer);
    }

    private void setUsers(JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> jdbcConfigurer) {
        if (securityProperties.getUsers().isEmpty()) {
            log.warn("No user added");
        }

        securityProperties.getUsers().forEach((key, value) -> {
            try {
                jdbcConfigurer
                        .withUser(key)
                        .password(passwordEncoder().encode(value))
                        .roles("USER");
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        });
    }

    private void setAdmins(JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> jdbcConfigurer) {
        if (securityProperties.getAdmins().isEmpty()) {
            log.warn("No admin added");
        }

        securityProperties.getAdmins().forEach((key, value) -> {
            try {
                jdbcConfigurer
                        .withUser(key)
                        .password(passwordEncoder().encode(value))
                        .roles("ADMIN");
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity httpSecurity ) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/tours/refresh").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/tours").hasAnyAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureHandler(handlers.failureHandler())
                .and()
                .exceptionHandling()
                .accessDeniedHandler(handlers.accessDeniedHandler())
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
