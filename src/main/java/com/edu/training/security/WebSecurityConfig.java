package com.edu.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Config for Login Form
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/class-management").permitAll()       // class-management and class-detail
                .antMatchers("/trainee-management").permitAll()     // trainee-management and trainee-detai              
                .antMatchers("/change-password").authenticated()
                .antMatchers("/general-management").authenticated() // subject-list and subject-details
                .antMatchers("/general-management/trainee-list").authenticated()
                .antMatchers("/general-management/trainer-list").authenticated()
                .antMatchers("/general-management/subject-list").authenticated()
                .antMatchers("/general-management/subject-list/subject-details").authenticated()
                .antMatchers("/logout").authenticated()
                .antMatchers("/download-templates").authenticated()
                .and()
            .formLogin() // Submit URL of login page.
                .loginPage("/login")//
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/change-password")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccessful");
                // .and()
            // .exceptionHandling()
            //     .accessDeniedPage("/404");

    }
}
