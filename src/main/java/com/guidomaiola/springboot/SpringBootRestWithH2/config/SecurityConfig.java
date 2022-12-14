package com.guidomaiola.springboot.SpringBootRestWithH2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.guidomaiola.springboot.SpringBootRestWithH2.auth.security.JwtAuthenticationFilter;
import com.guidomaiola.springboot.SpringBootRestWithH2.auth.security.JwtTokenProvider;

@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain springWebFilterChain(HttpSecurity http,
                                             JwtTokenProvider tokenProvider) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(c-> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .authorizeRequests(c -> c
                        .antMatchers("/auth/signin").permitAll()
                        .antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
                        .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
    
    @Bean
    AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        return authentication -> {
            String username = authentication.getPrincipal() + "";
            String password = authentication.getCredentials() + "";
            
            UserDetails user = userDetailsService.loadUserByUsername(username);
            
            if (!encoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Bad credentials");
            }
            
            if (!user.isEnabled()) {
                throw new DisabledException("User account is not active");
            }
            
            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
