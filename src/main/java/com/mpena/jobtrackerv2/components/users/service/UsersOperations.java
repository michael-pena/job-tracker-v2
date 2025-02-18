package com.mpena.jobtrackerv2.components.users.service;

import com.mpena.jobtrackerv2.components.users.dto.UserAddAuthorityDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserAddAuthorityResponseDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserCreateDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserResponseDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserUpdateDTO;

public interface UsersOperations {

    UserResponseDTO getUsersByUsername(String username);
    UserResponseDTO createUsers(UserCreateDTO createDTO);
    UserResponseDTO updateUsers(long userId, UserUpdateDTO updateDTO);
    void deleteUsers(long userId);
    UserAddAuthorityResponseDTO addUserAuthortiy(UserAddAuthorityDTO addAuthorityDTO);
}
