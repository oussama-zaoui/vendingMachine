package com.oussama.vendingmachine.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration @EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/login/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/user/buy").hasAuthority("ROLE_buyer");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH,"/user/deposit/**").hasAuthority("ROLE_buyer");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH,"/user/reset").hasAuthority("ROLE_buyer");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/user/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/user/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/product/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/product/**").hasAuthority("ROLE_seller");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH,"/product/**").hasAuthority("ROLE_seller");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/product/**").hasAuthority("ROLE_seller");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
