package com.angelly12.taskmanager.core.infrastructure.web.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ApiResponses {

    public static <T> ResponseEntity<ResponseSuccess<T>> success(
            String message,
            T data
    ){
        return ResponseEntity.ok(
                new ResponseSuccess<>(true, message, data)
        );
    }

    public static ResponseEntity<ResponseSuccess<Void>> success(
            String message
    ){
        return ResponseEntity.ok(
                new ResponseSuccess<>(true, message, null)
        );
    }

    public static <T> ResponseEntity<ResponseSuccess<T>> created(
            String message,
            T data
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseSuccess<>(true, message, data));
    }

    public static ResponseEntity<ResponseSuccess<Void>> created(
            String message
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseSuccess<>(true, message, null));
    }

    public static ResponseEntity<Void> noContent()
    {
        return ResponseEntity.noContent().build();
    }
}
