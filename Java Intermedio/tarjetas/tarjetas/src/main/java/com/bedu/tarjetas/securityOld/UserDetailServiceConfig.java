/*

package com.bedu.tarjetas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserOld;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailServiceConfig {

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(
                UserOld.withUsername("supervisor")
                        .password(bCryptPasswordEncoder.encode("123"))
                        .roles("SUPERVISOR")
                        .build());

        manager.createUser(
                UserOld.withUsername("operador")
                        .password(bCryptPasswordEncoder.encode("123"))
                        .roles("OPERADOR")
                        .build()
        );

        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

*/
