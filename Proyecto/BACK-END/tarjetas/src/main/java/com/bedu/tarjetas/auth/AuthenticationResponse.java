package com.bedu.tarjetas.auth;

import com.bedu.tarjetas.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private  Integer idUser;
    private String userName;
    private String email;
    private String token;
    private boolean ok;
    private Role role;
}
