package com.mpena.jobtrackerv2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status_code;
    private String status;
    private String message;
}
