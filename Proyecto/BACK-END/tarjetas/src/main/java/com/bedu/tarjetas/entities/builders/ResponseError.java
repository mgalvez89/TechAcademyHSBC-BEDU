package com.bedu.tarjetas.entities.builders;

import java.time.LocalDateTime;
import java.util.Map;

public class ResponseError {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private Map<String, String> errors;
    private String path;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

