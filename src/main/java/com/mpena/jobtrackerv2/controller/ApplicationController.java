package com.mpena.jobtrackerv2.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mpena.jobtrackerv2.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.service.ApplicationService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
public class ApplicationController {

    public static final String APPLICATION_PATH = "/api/v1/application";
    public static final String APPLICATION_PATH_BY_ID = APPLICATION_PATH + "/{applicationId}";

    private final ApplicationService applicationService;

    @PostMapping(APPLICATION_PATH)
    public ResponseEntity<ApplicationResponseDTO> saveNewApplication(@RequestBody ApplicationCreateDTO createDTO) {        
        ApplicationResponseDTO responseDTO = applicationService.createApplication(createDTO);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.LOCATION, APPLICATION_PATH + "/" + responseDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(responseDTO);
    }

    @GetMapping(APPLICATION_PATH_BY_ID)
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable("applicationId") Integer applicationId) {
        ApplicationResponseDTO responseDTO = applicationService.getApplicationById(applicationId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping(APPLICATION_PATH)
    public ResponseEntity<List<ApplicationResponseDTO>> getAllApplications() {
        List<ApplicationResponseDTO> applicationListDTO = applicationService.getAllApplications();
        return ResponseEntity.ok().body(applicationListDTO);
    }
    

    @DeleteMapping(APPLICATION_PATH_BY_ID)
    public ResponseEntity<?> deleteApplicationById(@PathVariable("applicationId") Integer applicationId) {
        applicationService.deleteApplication(applicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
