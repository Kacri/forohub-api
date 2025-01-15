package com.karinadev.forohub_api.Exeption;

import com.karinadev.forohub_api.Modelos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidacionException .class)
    public ResponseEntity<ErrorResponse> handleValidacionException(ValidacionException ex){
        ErrorResponse errorResponse = new ErrorResponse("VALIDACION_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
