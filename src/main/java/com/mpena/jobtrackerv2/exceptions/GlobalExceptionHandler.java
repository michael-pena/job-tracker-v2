package com.mpena.jobtrackerv2.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, String>>> handleBindErrors(MethodArgumentNotValidException ex) {
        
        List<Map<String, String>> errorList = ex.getFieldErrors()
        .stream()
        .map( fieldError -> {
            Map<String,String> errorMap = new HashMap<>();
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            return errorMap;
        })
        .toList();

        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleInvalidParams(MethodArgumentTypeMismatchException ex) {
        
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<List<Map<String, String>>> handleJPAViolations(TransactionSystemException ex) {

        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();

        if (ex.getCause().getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException ve = (ConstraintViolationException) ex.getCause().getCause();

            List<Map<String, String>> errors = ve.getConstraintViolations()
            .stream()
            .map( constraintViolation -> {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                return errorMap;
            }).toList();
            
            return responseEntity.body(errors);
        }
    
        return responseEntity.build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHTTPMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), 
            HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), "HTTP method not allowed for this endpoint");

        return ResponseEntity.badRequest().body(errorResponse);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), 
            HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleonstraintViolationException(ConstraintViolationException ex) {
        
        StringBuilder sbViolations = new StringBuilder(); 
        
        ex.getConstraintViolations()
            .stream()
            .forEach(str -> sbViolations.append(str + ", "));

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), 
            HttpStatus.BAD_REQUEST.getReasonPhrase(), sbViolations.toString());

        return ResponseEntity.badRequest().body(errorResponse);

    }


}
