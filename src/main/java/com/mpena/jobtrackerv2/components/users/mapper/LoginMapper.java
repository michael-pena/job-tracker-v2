package com.mpena.jobtrackerv2.components.users.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginMapper {
    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);
}
