package com.example.tdams.security;

import com.example.tdams.filter.CustomAuthenticationFilter;
import com.example.tdams.filter.CustomAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.cors();
        http.authorizeHttpRequests().antMatchers("/api/login/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/api/register/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN");
        http.authorizeHttpRequests().antMatchers("/api/role/**").hasAuthority("ROLE_ADMIN");
        http.authorizeHttpRequests().antMatchers("/api/cust/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOMER");
        http.authorizeHttpRequests().antMatchers("/api/item/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER", "ROLE_VENDOR", "ROLE_DELIVERY_PERSONNEL");
        http.authorizeHttpRequests().antMatchers("/api/order/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOMER", "ROLE_DELIVERY_PERSONNEL");
        http.authorizeHttpRequests().antMatchers("/api/tiffin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER");
        http.authorizeHttpRequests().antMatchers("/api/tiffindetail/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER");
        http.authorizeHttpRequests().antMatchers("/api/tiffin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER");
        http.authorizeHttpRequests().antMatchers("/api/vendor/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_VENDOR");
        http.authorizeHttpRequests().antMatchers("/api/deliveryp/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_DELIVERY_PERSONNEL");
        http.authorizeHttpRequests().antMatchers("/api/user/**").authenticated();
        http.authorizeHttpRequests().antMatchers("/api/address/**").authenticated();
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}

