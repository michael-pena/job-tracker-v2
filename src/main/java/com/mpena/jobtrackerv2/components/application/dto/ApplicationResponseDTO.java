package com.mpena.jobtrackerv2.components.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationResponseDTO {

    @JsonIgnore
    private Integer id;
    private String company;
    private String position;
    private String date;
    private String status;
    private String offer;
    private String accepted;
}
