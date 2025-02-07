package com.mpena.jobtrackerv2.components.users.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mpena.jobtrackerv2.components.users.dto.UserCreateDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserResponseDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserUpdateDTO;
import com.mpena.jobtrackerv2.components.users.model.Users;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users toEntity(UserUpdateDTO userUpdateDTO);
    Users toEntity(UserCreateDTO userCreateDTO);
    UserResponseDTO toDto(Users userEntity);

}
