package com.leleo.mytasks.infra;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ValidationErrorMessage extends RestErrorMessage {

    Map<String, String> fields;

    public ValidationErrorMessage(HttpStatus status, String message, Map<String, String> fields) {
        super(status, message);
        this.fields = fields;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
