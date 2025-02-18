package com.mpena.jobtrackerv2.config;

import com.mpena.jobtrackerv2.components.application.controller.ApplicationController;
import com.mpena.jobtrackerv2.components.auth.controller.AuthController;
import com.mpena.jobtrackerv2.components.users.controller.UsersController;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final RsaKeyProperties jwtConfigProperties;

    private final static String[] allowedPaths = {UsersController.USERS_PATH_REGISTER, AuthController.AUTH_TOKEN};

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable) // TODO: testing - fix with proper config
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( (requests) -> {
                    requests
                            .requestMatchers(allowedPaths).permitAll()
                            .requestMatchers(HttpMethod.GET, ApplicationController.APPLICATION_PATH, ApplicationController.APPLICATION_PATH_BY_ID).hasAuthority("SCOPE_read")
                            .requestMatchers(HttpMethod.POST, ApplicationController.APPLICATION_PATH).hasAuthority("SCOPE_edit")
                            .requestMatchers(HttpMethod.PUT, ApplicationController.APPLICATION_PATH_BY_ID).hasAuthority("SCOPE_edit")
                            .requestMatchers(HttpMethod.DELETE, ApplicationController.APPLICATION_PATH_BY_ID).hasAuthority("SCOPE_edit")
                            .requestMatchers(UsersController.USERS_PATH_AUTHORITY).hasAuthority("SCOPE_admin");
                            //TODO: Add the RUD endpoints for users - has authority: SCOPE_Admin
                            //TODO: permitAll() to the swagger pages
                } )
                .oauth2ResourceServer(oauth2Config -> oauth2Config.jwt(Customizer.withDefaults()))
                .httpBasic(Customizer.withDefaults())
            .build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, 
    PasswordEncoder passwordEncoder) {

        JobTrackerUsernamePwdAuthenticationProvider authenticationProvider = 
            new JobTrackerUsernamePwdAuthenticationProvider(userDetailsService, passwordEncoder);

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(jwtConfigProperties.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(jwtConfigProperties.publicKey())
                .privateKey(jwtConfigProperties.privateKey())
                .build();

        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix("SCOPE_");
        converter.setAuthoritiesClaimName("scopes");
        return converter;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return converter;
    }
}
