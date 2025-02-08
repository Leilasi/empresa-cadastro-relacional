package com.cadastro.relacional.empresa.controller.exception.handler;

import com.cadastro.relacional.empresa.controller.exception.StandardError;
import com.cadastro.relacional.empresa.controller.exception.ValidationError;
import com.cadastro.relacional.empresa.service.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        LOGGER.error("error de validacão de campos ", ex);

        final HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        final ValidationError error = new ValidationError(
                Instant.now().toEpochMilli(),
                status.value(),
                "Erro de validação",
                ex.getMessage(),
                request.getRequestURI()
        );

        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError field : fieldErrors) {
            LOGGER.error("error ao validar o campo {}: {}", field.getField(), field.getDefaultMessage());
            error.addError(field.getField(), field.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmpresaNotFoundException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(EmpresaNotFoundException ex, HttpServletRequest request) {
        LOGGER.error("entidade não encontrada ", ex);
        final HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(
                Instant.now().toEpochMilli(),
                status.value(),
                "Entidade não encontrada",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(UsuarioNotFoundException ex, HttpServletRequest request) {
        LOGGER.error("entidade não encontrada ", ex);
        final HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(
                Instant.now().toEpochMilli(),
                status.value(),
                "Entidade não encontrada",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidEmpresaException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(InvalidEmpresaException ex, HttpServletRequest request) {
        LOGGER.error("error ao processar a requisão ", ex);
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(
                Instant.now().toEpochMilli(),
                status.value(),
                "Error ao processar requisição",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidUsuarioException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(InvalidUsuarioException ex, HttpServletRequest request) {
        LOGGER.error("error ao processar a requisão ", ex);
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(
                Instant.now().toEpochMilli(),
                status.value(),
                "Error ao processar requisição",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }


    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(InvalidParameterException ex, HttpServletRequest request) {
        LOGGER.error("parâmetros enviado na requisão inválidos ", ex);
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(
                Instant.now().toEpochMilli(),
                status.value(),
                "Parâmetros da requisão inválido",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler( IllegalArgumentException.class)
    public ResponseEntity<StandardError> handleResponseStatusException( IllegalArgumentException ex, HttpServletRequest request) {
        LOGGER.error("argumento inválido ", ex);
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(
                Instant.now().toEpochMilli(),
                status.value(),
                "Parâmetros da requisão inválido",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleResponseStatusException(Exception ex, HttpServletRequest request) {
        LOGGER.error("Erro ao tentar processar o servidor ", ex);
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError error = new StandardError(
                Instant.now().toEpochMilli(),
                status.value(),
                "Erro ao tentar processar o servidor",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        LOGGER.error("Erro ao processar requisição ", ex);
        StandardError error = new StandardError(
                Instant.now().toEpochMilli(),
                ex.getStatusCode().value(),
                "Erro ao processar requisição",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

}
