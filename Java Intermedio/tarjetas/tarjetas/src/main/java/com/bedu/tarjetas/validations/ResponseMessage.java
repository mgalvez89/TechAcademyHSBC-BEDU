
package com.bedu.tarjetas.validations;

import java.time.LocalDateTime;
import java.util.Map;

public class ResponseMessage {


    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String message;
    private Map<String, String> errors;
    private String path;

    
    public ResponseMessage(Map<String, String> errors, int status, String path) {
        this.errors = errors;
        this.status = status;
        this.path = path;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}

