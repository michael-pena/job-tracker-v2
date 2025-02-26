package com.mpena.jobtrackerv2.components.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationUpdateDTO {

    @NotEmpty
    @Size(min = 1, max=100, message = "company must be less than 100 characters")
    private String company;
    
    @NotEmpty
    @Size(min = 1, max=100, message = "position must be less than 100 characters")
    private String position;

    @NotEmpty
    @Size(min = 1, max=30, message = "date must be less than 30 characters") 
    private String date;

    @NotEmpty
    @Size(min = 1, max=15, message = "status must be less than 15 characters")
    private String status;
    private String offer;
    private String accepted;
}
