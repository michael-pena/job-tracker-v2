package com.mpena.jobtrackerv2.components.auth.controller;

import com.mpena.jobtrackerv2.components.auth.dto.LoginRequestDTO;
import com.mpena.jobtrackerv2.components.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    public static final String AUTH_TOKEN = "api/v1/token";

    private final AuthService authService;
    
    @PostMapping(AUTH_TOKEN)
    public ResponseEntity<String> getToken(@RequestBody LoginRequestDTO loginRequestDTO) {

        log.info("Token requested for user: '{}'", loginRequestDTO.getUsername());

        String token = authService.generateToken(loginRequestDTO);

        log.info("Result of Auth token: '{}'", token);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(token);
    }
}
