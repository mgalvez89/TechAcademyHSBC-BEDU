/*
package com.bedu.tarjetas.securityOld;

import com.bedu.tarjetas.repositories.IUserInRoleRepository;
import com.bedu.tarjetas.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailsServiceConfig {

    private IUserRepository iUserRepository;
    private IUserInRoleRepository iUserInRoleRepository;

    @Autowired
    public UserDetailsServiceConfig(IUserRepository iUserRepository, IUserInRoleRepository iUserInRoleRepository){
        this.iUserRepository = iUserRepository;
        this.iUserInRoleRepository = iUserInRoleRepository;
    }

  */
/* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserOld> optionalUser = iUserRepository.findByUsername(username);
       List<GrantedAuthority> authorizedRoles = new ArrayList<GrantedAuthority>();

        if(optionalUser.isPresent()){
            UserOld user = optionalUser.get();
            List<UserInRole> userInRoleList = iUserInRoleRepository.findByUser(user);
            userInRoleList.forEach((r)->{
                authorizedRoles.add(new SimpleGrantedAuthority(r.getRole().getName()));
            });
            authorizedRoles.forEach(System.out::println);
            return new org.springframework.security.core.userdetails.UserOld(
                    user.getUsername(),
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    authorizedRoles
                    );
        }
        else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

    }*//*


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) iUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UserOld not found"));
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
*/
