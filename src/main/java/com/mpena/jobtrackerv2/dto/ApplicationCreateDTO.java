package com.mpena.jobtrackerv2.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationCreateDTO {
    
    @NotEmpty
    private String company;

    @NotEmpty
    private String position;

    @NotEmpty 
    private String date;

    @NotEmpty
    private String status;
    private String offer;
    private String accepted;
}
