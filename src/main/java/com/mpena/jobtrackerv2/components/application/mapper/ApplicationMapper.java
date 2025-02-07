package com.mpena.jobtrackerv2.components.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mpena.jobtrackerv2.components.application.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationUpdateDTO;
import com.mpena.jobtrackerv2.components.application.model.Application;

@Mapper
public interface ApplicationMapper {
    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    Application toEntity(ApplicationCreateDTO applicationCreateDTO);
    Application toEntity(ApplicationUpdateDTO applicationUpdateDTO);
    ApplicationResponseDTO toDTO(Application applicationEntity);
}
