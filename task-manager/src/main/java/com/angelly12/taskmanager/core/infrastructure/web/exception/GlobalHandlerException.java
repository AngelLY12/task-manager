package com.angelly12.taskmanager.core.infrastructure.web.exception;

import com.angelly12.taskmanager.core.domain.exceptions.DomainException;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ResponseError> domainException(DomainException e)
    {
        ResponseError response = new ResponseError(
                e.getStatusCode(),
                e.getMessage(),
                e.getErrorCode().name()
        );

        return ResponseEntity.status(e.getStatusCode())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> validationException(
            MethodArgumentNotValidException e
    ){
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Invalid request");

        ResponseError response = new ResponseError(
                422,
                message,
                "VALIDATION_ERROR"
        );

        return ResponseEntity.unprocessableContent().body(response);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> invalidBodyException(
            HttpMessageNotReadableException e) {

        ResponseError response = new ResponseError(
                400,
                "Invalid request body: check field types and allowed values",
                "INVALID_REQUEST_BODY"
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseError> invalidParameterException(
            MethodArgumentTypeMismatchException e) {

        String message = "Invalid value for parameter: " + e.getName();

        ResponseError response = new ResponseError(
                400,
                message,
                "INVALID_PARAMETER"
        );

        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> unexpectedException(Exception e)
    {
        ResponseError response = new ResponseError(
                500,
                "Unexpected error",
                "INTERNAL_SERVER_ERROR"
        );

        return ResponseEntity.internalServerError().body(response);
    }
}
