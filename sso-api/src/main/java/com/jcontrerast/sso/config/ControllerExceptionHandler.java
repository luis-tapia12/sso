package com.jcontrerast.sso.config;

import com.jcontrerast.sso.dto.ServiceResponseDTO;
import com.jcontrerast.utils.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceResponseDTO> handleException(MethodArgumentNotValidException e) {
        ServiceResponseDTO response = ServiceResponseDTO.create("Validation failed");

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            response.addDetail("The field '" + error.getField() + "' is invalid: " + error.getDefaultMessage());
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ServiceResponseDTO> handleException(ResourceNotFoundException e) {
        ServiceResponseDTO response = ServiceResponseDTO.create("The requested resource could not be found");

        response.addDetail(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ServiceResponseDTO> handleException(DataIntegrityViolationException e) {
        ServiceResponseDTO response = ServiceResponseDTO.create("Data integrity violation");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
