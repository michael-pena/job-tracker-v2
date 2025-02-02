package com.mpena.jobtrackerv2.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationResponseDTO {

    private Integer id;
    private String company;
    private String position;
    private String date;
    private String status;
    private String offer;
    private String accepted;
}
