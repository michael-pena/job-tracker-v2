package com.mpena.jobtrackerv2.components.users.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mpena.jobtrackerv2.components.users.dto.UserAddAuthorityDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserAddAuthorityResponseDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserCreateDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserResponseDTO;
import com.mpena.jobtrackerv2.components.users.service.UsersService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@Tag(name = "User", 
    description = "REST API endpoints for creating users and adding new permissions to user accounts")
public class UsersController {

    public static final String USERS_PATH = "/api/v1/user";
    public static final String USERS_PATH_ID = USERS_PATH + "/{userId}";
    public static final String USERS_PATH_REGISTER = USERS_PATH + "/register";
    public static final String USERS_PATH_AUTHORITY = USERS_PATH + "/authority";

    private final UsersService usersService;

    @PostMapping(USERS_PATH_REGISTER)
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreateDTO userDTO) {
        UserResponseDTO responseDTO = usersService.createUsers(userDTO);
        URI uri = UriComponentsBuilder.fromPath(USERS_PATH_ID).build(responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PostMapping(USERS_PATH_AUTHORITY)
    public ResponseEntity<UserAddAuthorityResponseDTO> addAuthorityToUser(@RequestBody @Valid UserAddAuthorityDTO addAuthorityDTO) {
        UserAddAuthorityResponseDTO responseDTO = usersService.addUserAuthortiy(addAuthorityDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    
    //TODO: create the RUD endpoints for managing users
}
