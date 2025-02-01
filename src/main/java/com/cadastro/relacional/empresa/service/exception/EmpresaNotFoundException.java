package com.cadastro.relacional.empresa.service.exception;

public class EmpresaNotFoundException extends RuntimeException {

    public EmpresaNotFoundException(String message) {
        super(message);
    }
}
