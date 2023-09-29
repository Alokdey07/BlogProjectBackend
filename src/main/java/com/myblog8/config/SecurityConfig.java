package com.myblog8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET,"/api/**").permitAll().anyRequest().authenticated().and().httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails user = User.builder().username("Pankaj").
                password(getEncoder().encode("password")).roles("USER").
                build();

        UserDetails admin = User.builder().username("admin")
                .password(getEncoder()
                .encode("admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(admin,user);

        //wHAT IS IN MEMORY AUTHANTICATION
        //WHEN IMAGINE 7-8 ROLES ARE THEREFIRST YOU WANT TO DO AUTHORIZAAATION WITHOUT DEALING WITH dATABASE
        //THEN YOU CREATE OBJECT LIKE THIS WITH THE ROLES AND DO YOUR TESTING WITHOUT DATABASE
        //LATTER ONWE CAN STORE OUR USERNAME AND PASSWORD IN OUR DATABASE

    }

}
