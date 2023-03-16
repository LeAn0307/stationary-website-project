package com.shinhands.mu.Stationary.security;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/accounts/login", "/accounts/signup","/admin/**").permitAll()
                .antMatchers("/js/**", "/css/**", "/images/**", "/vendors/**").permitAll()
                .antMatchers("/products/**").permitAll()
//                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/api/**").hasAuthority("USER")
                        .anyRequest().authenticated()
                .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/admin/dashboard", true).permitAll()
//                    .failureForwardUrl("/error/404")
//                .and()
//                .logout()
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/login?logout")
//                    .permitAll()
//                .and()
                .exceptionHandling()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
