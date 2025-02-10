package com.mpena.jobtrackerv2.components.application.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mpena.jobtrackerv2.components.application.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationUpdateDTO;
import com.mpena.jobtrackerv2.components.application.service.ApplicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequiredArgsConstructor
public class ApplicationController {

    public static final String APPLICATION_PATH = "/api/v1/application";
    public static final String APPLICATION_PATH_BY_ID = APPLICATION_PATH + "/{applicationId}";

    private final ApplicationService applicationService;

    @PostMapping(APPLICATION_PATH)
    public ResponseEntity<ApplicationResponseDTO> saveNewApplication(@RequestBody @Valid ApplicationCreateDTO createDTO) {        
        ApplicationResponseDTO responseDTO = applicationService.createApplication(createDTO);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.LOCATION, APPLICATION_PATH + "/" + responseDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(responseDTO);
    }

    @PutMapping(APPLICATION_PATH_BY_ID)
    public ResponseEntity<ApplicationResponseDTO> updateApplication(@PathVariable("applicationId") Long applicationId, @RequestBody @Valid ApplicationUpdateDTO updateDTO) {        
        ApplicationResponseDTO responseDTO = applicationService.updateApplicationById(applicationId, updateDTO);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.LOCATION, APPLICATION_PATH + "/" + responseDTO.getId());
        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(responseDTO);
    }

    @GetMapping(APPLICATION_PATH_BY_ID)
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable("applicationId") Long applicationId) {
        ApplicationResponseDTO responseDTO = applicationService.getApplicationById(applicationId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping(APPLICATION_PATH)
    public ResponseEntity<Page<ApplicationResponseDTO>> getPageOfApplications(
        @RequestParam(defaultValue = "0", required = false, name="page") Integer page,
        @RequestParam(defaultValue = "10", required = false, name = "size") Integer size,
            @RequestParam(defaultValue = "id", required = false, name = "sortBy") String sortBy,
            @RequestParam(defaultValue = "asc", required = false, name="sortDir") String sortDir) {
        Sort.Direction direction = Sort.Direction.fromString(sortDir);
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<ApplicationResponseDTO> applicationPage = applicationService.getPageOfApplications(pageable);
        return ResponseEntity.ok().body(applicationPage);
    }
    
    @DeleteMapping(APPLICATION_PATH_BY_ID)
    public ResponseEntity<?> deleteApplicationById(@PathVariable("applicationId") Long applicationId) {
        applicationService.deleteApplication(applicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
