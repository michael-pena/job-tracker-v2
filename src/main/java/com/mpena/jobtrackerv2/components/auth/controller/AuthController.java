package com.mpena.jobtrackerv2.components.auth.controller;

import com.mpena.jobtrackerv2.components.auth.dto.TokenRequestDTO;
import com.mpena.jobtrackerv2.components.auth.dto.TokenResponseDTO;
import com.mpena.jobtrackerv2.components.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Auth", 
    description = "Endpoint for performing authentication to obtain JWT for accessing Application and User endpoints")
public class AuthController {
    public static final String AUTH_TOKEN = "auth/token";

    private final AuthService authService;
    
    @Operation(summary = "Obtain a JWT", description = "Endpoint for obtaining a JWT using user credentials")
    @PostMapping(AUTH_TOKEN)
    public ResponseEntity<TokenResponseDTO> getToken(@RequestBody @Valid TokenRequestDTO tokenRequestDTO) {

        log.debug("Token requested for user: '{}'", tokenRequestDTO.getUsername());

        TokenResponseDTO responseDTO = authService.generateToken(tokenRequestDTO);

        return ResponseEntity.ok().body(responseDTO);
    }
}
