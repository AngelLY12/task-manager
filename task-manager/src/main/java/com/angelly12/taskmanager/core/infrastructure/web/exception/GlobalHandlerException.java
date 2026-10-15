package com.angelly12.taskmanager.core.infrastructure.web.exception;

import com.angelly12.taskmanager.core.domain.exceptions.DomainException;
import com.angelly12.taskmanager.core.infrastructure.web.dto.response.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
