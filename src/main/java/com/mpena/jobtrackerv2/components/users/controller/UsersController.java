package com.mpena.jobtrackerv2.components.users.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mpena.jobtrackerv2.components.users.dto.UserCreateDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserResponseDTO;
import com.mpena.jobtrackerv2.components.users.service.UsersService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class UsersController {

    public static final String USERS_PATH = "/api/v1/user";
    public static final String USERS_PATH_ID = USERS_PATH + "/{userId}";

    private final UsersService usersService;

    @PostMapping(USERS_PATH)
    public ResponseEntity<UserResponseDTO> postMethodName(@RequestBody UserCreateDTO userDTO) {
        UserResponseDTO responseDTO = usersService.createUsers(userDTO);
        URI uri = UriComponentsBuilder.fromPath(USERS_PATH_ID).build(responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }
    
}
