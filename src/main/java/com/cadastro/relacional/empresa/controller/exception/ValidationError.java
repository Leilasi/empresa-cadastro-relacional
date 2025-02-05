package com.cadastro.relacional.empresa.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> fieldErrors = new ArrayList<>();


    public ValidationError(long timestamp, int status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public void addError(String field, String message) {
        fieldErrors.add(new FieldMessage(field, message));
    }

    public List<FieldMessage> getFieldErrors() {
        return fieldErrors;
    }
}
