package com.mpena.jobtrackerv2.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mpena.jobtrackerv2.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.service.ApplicationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class ApplicationController {

    public static final String APPLICATION_PATH = "/api/v1/application";

    private final ApplicationService applicationService;

    @PostMapping(APPLICATION_PATH)
    public ResponseEntity<ApplicationResponseDTO> postMethodName(@RequestBody ApplicationCreateDTO createDTO) {        
        ApplicationResponseDTO responseDTO = applicationService.createApplication(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }
    
}
