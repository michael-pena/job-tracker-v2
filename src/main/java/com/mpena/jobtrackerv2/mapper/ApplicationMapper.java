package com.mpena.jobtrackerv2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mpena.jobtrackerv2.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.dto.ApplicationUpdateDTO;
import com.mpena.jobtrackerv2.model.Application;

@Mapper
public interface ApplicationMapper {
    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    Application toEntity(ApplicationCreateDTO applicationCreateDTO);
    Application toEntity(ApplicationUpdateDTO applicationUpdateDTO);
    ApplicationResponseDTO toDTO(Application applicationEntity);
}
