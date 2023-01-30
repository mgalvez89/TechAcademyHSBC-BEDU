package com.bedu.tarjetas.validations;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

public class ResponseErrorBuilder {

   private int status;
    private Map<String, String> errors;
    private String path;

    public ResponseErrorBuilder status( int status){
        this.status = status;
        return this;
    }

    public ResponseErrorBuilder status(HttpStatus status) {
        this.status = status.value();
        return this;
    }

    public ResponseErrorBuilder errors(Map<String, String> errors) {
        this.errors = errors;
        return this;
    }



    public ResponseErrorBuilder exception(SQLIntegrityConstraintViolationException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.status = status.value();
        String be = "Ya existe un registro con el mismo nombre, favor de verificar: ";
        this.errors = new HashMap<>();
        String message = ex.getMessage();
        errors.put("message", be + message);

        return this;
    }

    public ResponseErrorBuilder exception(java.lang.IllegalStateException ex)
    {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.status = status.value();
        this.errors = new HashMap<>();
        String extramessage = "El número de tarjetas NO puede contener letras o caracteres especiales, favor de verificar: ";
        String message = ex.getMessage();
        errors.put("message", extramessage + message);

        return this;
    }

    public ResponseErrorBuilder exception(java.lang.NumberFormatException ex)
    {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.status = status.value();
        this.errors = new HashMap<>();
        String extramessage = "El parametro a ingresar NO puede estar vacío o contener letras y caracteres especiales, favor de verificar: ";
        String message = ex.getMessage();
        errors.put("message", extramessage + message);

        return this;
    }

    public ResponseErrorBuilder exception(HttpMessageNotReadableException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.status = status.value();
        String be = "El campo 'capacity' NO acepta letras solo permite números, favor de verificar: ";
        this.errors = new HashMap<>();
        String message = ex.getMessage();
        errors.put("message", be + message);

        return this;
    }

    public ResponseErrorBuilder exception(MethodArgumentNotValidException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.status = status.value();

        this.errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return this;
    }
    public ResponseErrorBuilder exception(ConstraintViolationException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.status = status.value();

        this.errors = new HashMap<>();
        ex.getConstraintViolations().forEach(
                (error) ->{
                    String propertyPath = String.valueOf(error.getPropertyPath());
                    String errorMessage = error.getMessage();
                    errors.put(propertyPath, errorMessage);
                });

        return this;
    }

    public ResponseErrorBuilder path(String path) {
        this.path = path;
        return this;
    }

    public ResponseError build() {
        ResponseError response = new ResponseError();
        response.setStatus(status);
        response.setErrors(errors);
        response.setPath(path);
        return response;
    }

    public ResponseEntity<ResponseError> entity() {
        return ResponseEntity.status(status).headers(HttpHeaders.EMPTY).body(build());
    }


}
