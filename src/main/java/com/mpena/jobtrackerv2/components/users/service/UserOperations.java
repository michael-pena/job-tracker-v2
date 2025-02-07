package com.mpena.jobtrackerv2.components.users.service;

import com.mpena.jobtrackerv2.components.users.dto.UserResponseDTO;
import com.mpena.jobtrackerv2.components.users.model.Users;

public interface UserOperations {

    UserResponseDTO getUsersByUsername(String username);
    UserResponseDTO createUsers(Users userEntity);
    UserResponseDTO updateUsers(int userId, Users userEntity);
    void deleteUsers(int userId);
    //login user
    //authenticate user
}
