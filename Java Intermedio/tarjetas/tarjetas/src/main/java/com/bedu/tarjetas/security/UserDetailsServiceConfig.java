/*
package com.bedu.tarjetas.configuration;

import com.bedu.tarjetas.entities.Role;
import com.bedu.tarjetas.entities.User;
import com.bedu.tarjetas.entities.UserInRole;
import com.bedu.tarjetas.repositories.IUserInRoleRepository;
import com.bedu.tarjetas.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class UserDetailsServiceConfig implements org.springframework.security.core.userdetails.UserDetailsService {

    private IUserRepository iUserRepository;
    private IUserInRoleRepository iUserInRoleRepository;

    @Autowired
    public UserDetailsServiceConfig(IUserRepository iUserRepository, IUserInRoleRepository iUserInRoleRepository){
        this.iUserRepository = iUserRepository;
        this.iUserInRoleRepository = iUserInRoleRepository;
    }

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = iUserRepository.findByUsername(username);
       List<GrantedAuthority> authorizedRoles = new ArrayList<GrantedAuthority>();

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            List<UserInRole> userInRoleList = iUserInRoleRepository.findByUser(user);
            userInRoleList.forEach((r)->{
                authorizedRoles.add(new SimpleGrantedAuthority(r.getRole().getName()));
            });

            return new org.springframework.security.core.userdetails.User(
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

    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
*/
