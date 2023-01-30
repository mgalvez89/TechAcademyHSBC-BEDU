package com.bedu.tarjetas.entities.handlers;

import com.bedu.tarjetas.entities.builders.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.TreeMap;



@RestControllerAdvice

public class GlobalManagerExceptions extends ResponseEntityExceptionHandler {


  /*  @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers
            , HttpStatusCode status
            , WebRequest request) {

        Map<String, String> errors = new TreeMap<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()){
            errors.put(error.getObjectName(), error.getDefaultMessage());
        }

        ResponseError responseError = new ResponseError();
        responseError.setErrors(errors);
        responseError.setPath(request.getDescription(false).substring(4));
        responseError.setStatus(status.value());
        return handleExceptionInternal(ex, responseError, headers, HttpStatus.BAD_REQUEST, request);

    }*/

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex
            , HttpHeaders headers
            , HttpStatusCode status
            , WebRequest request) {
        Map<String, String> errors = new TreeMap<>();

        StringBuilder builder = new StringBuilder();
        builder.append("El método ");
        builder.append(ex.getMethod());
        builder.append(" no está soportado para esta petición. Los métodos soportados son ");

        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        errors.put("Error", builder.toString());
        ResponseError responseError = new ResponseError();
        responseError.setErrors(errors);
        responseError.setStatus(status.value());
        responseError.setPath(request.getDescription(false).substring(4));

        return new ResponseEntity<Object>(responseError, headers, HttpStatus.METHOD_NOT_ALLOWED);
    }



}
