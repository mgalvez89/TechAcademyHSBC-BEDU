/*
package com.bedu.tarjetas.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Value("${spring.security.debug:false}")
    boolean securityDebug;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        */
/*http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/packages/**")
                .hasRole("OPERADOR")
                .requestMatchers(HttpMethod.POST,"/api/v1/deliveries/**")
                .hasRole("OPERADOR")
                .requestMatchers(HttpMethod.PUT,"/api/v1/deliveries/**")
                .hasRole("OPERADOR")
                .requestMatchers(HttpMethod.POST, "/api/v1/locations/**")
                .hasRole("SUPERVISOR")
                .requestMatchers(HttpMethod.GET, "/api/v1/request/**")
                .hasAnyRole("OPERADOR", "SUPERVISOR")
                .requestMatchers(HttpMethod.PUT)
                .hasRole("SUPERVISOR")
                .requestMatchers("/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);*//*

          http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.DELETE)
                .hasRole("ADMIN")
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")
                .requestMatchers("/api/v1/locations/**")
                .hasAnyRole("SUPERVISOR", "ADMIN")
                  .requestMatchers("/api/v1/deliveries/**")
                  .hasRole("OPERADOR")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
*/
