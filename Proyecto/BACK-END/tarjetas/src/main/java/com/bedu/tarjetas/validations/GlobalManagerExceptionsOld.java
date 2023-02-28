package com.bedu.tarjetas.validations;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalManagerExceptionsOld {


  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStatusException(MethodArgumentNotValidException ex, WebRequest request){
        return ResponseError.builder()
                .exception(ex)
                .path(request.getDescription(false).substring(4))
                .entity();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleDuplicateKey(SQLIntegrityConstraintViolationException ex, WebRequest request){
      return ResponseError.builder()
              .exception(ex)
              .path(request.getDescription(false).substring(4))
              .entity();
    }

    @ExceptionHandler(java.lang.IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(java.lang.IllegalArgumentException ex, WebRequest request){
      return ResponseError.builder()
              .exception(ex)
              .path(request.getDescription(false).substring(4))
              .entity();
    }

    @ExceptionHandler(java.lang.IllegalStateException.class)
    public ResponseEntity<?> handleIllegalState(java.lang.IllegalStateException ex, WebRequest request){
      return ResponseError.builder()
              .exception(ex)
              .path(request.getDescription(false).substring(4))
              .entity();
    }

    @ExceptionHandler(java.lang.NullPointerException.class)
    public ResponseEntity<?> handleNullPointer(java.lang.NullPointerException ex, WebRequest request){
      return ResponseError.builder()
              .exception(ex)
              .path(request.getDescription(false).substring(4))
              .entity();
    }

    @ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwt(io.jsonwebtoken.ExpiredJwtException ex, WebRequest request){
      return ResponseError.builder()
              .exception(ex)
              .path(request.getDescription(false).substring(4))
              .entity();
    }
    @ExceptionHandler(java.lang.NumberFormatException.class)
    public ResponseEntity<?> handleNumberFormat(java.lang.NumberFormatException ex, WebRequest request){
      return ResponseError.builder()
              .exception(ex)
              .path(request.getDescription(false).substring(4))
              .entity();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleNotReadable(HttpMessageNotReadableException ex, WebRequest request){
      return ResponseError.builder()
              .exception(ex)
              .path(request.getDescription(false).substring(4))
              .entity();

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstrainViolation(ConstraintViolationException ex, WebRequest request){
      return ResponseError.builder()
              .exception(ex)
              .path(request.getDescription(false).substring(4))
              .entity();
    }




    //PARA ERRORES EN GENERAL
    /*  @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return message;
    }*/

}
