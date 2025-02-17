package com.mpena.jobtrackerv2.components.auth.service;

import com.mpena.jobtrackerv2.components.auth.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthOperations{

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder encoder;

    @Override
    public String generateToken(LoginRequestDTO loginRequestDTO) {

        Authentication authRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        Authentication authenticationResponse = authenticationManager.authenticate(authRequest);

        if (authenticationResponse != null  && authenticationResponse.isAuthenticated()) {
            return createToken(authenticationResponse);
        }

        return "";
    }

    private String createToken(Authentication authentication) {
        Instant now = Instant.now();

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("JobTracker")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scopes", scope)
                .claim("username", authentication.getName())
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
