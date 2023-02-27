package com.bedu.tarjetas.auth;


import com.bedu.tarjetas.config.JwtService;
import com.bedu.tarjetas.token.Token;
import com.bedu.tarjetas.token.TokenRepository;
import com.bedu.tarjetas.token.TokenType;
import com.bedu.tarjetas.user.Role;
import com.bedu.tarjetas.user.User;
import com.bedu.tarjetas.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .idUser(user.getId())
                .userName(user.getFirstname())
                .email(user.getEmail())
                .token(jwtToken)
                .ok(user.isEnabled())
                .role(user.getRole())
                .build();
    }

    public AuthenticationResponse validate(String token) {

        if( !jwtService.isTokenExpired( token ) ){
            var user = repository.validateToken( token )
                    .orElseThrow();
            return AuthenticationResponse.builder()
                    .idUser(user.getId())
                    .userName(user.getFirstname())
                    .email(user.getEmail())
                    .token( token )
                    .ok(user.isEnabled())
                    .role(user.getRole())
                    .build();
        }

        return AuthenticationResponse.builder()
                .idUser( null )
                .userName("")
                .email("")
                .token( "" )
                .ok( false )
                .role( null )
                .build();

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}

