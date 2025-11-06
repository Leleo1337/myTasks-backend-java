package com.leleo.mytasks.exceptions;

import java.util.Map;

public class ValidationException extends RuntimeException {
    Map<String, String> fields;

    public ValidationException(String message, Map<String,String> fields) {
        super(message);
        this.fields = fields;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
