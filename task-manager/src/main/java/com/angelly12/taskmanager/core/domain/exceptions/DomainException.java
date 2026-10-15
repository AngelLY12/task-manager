package com.angelly12.taskmanager.core.domain.exceptions;

import com.angelly12.taskmanager.core.domain.enums.ErrorCode;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException{
    private final int statusCode;
    private final ErrorCode errorCode;
    public DomainException(
            String message,
            int statusCode,
            ErrorCode errorCode
    ){
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }
}
