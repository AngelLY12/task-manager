package com.angelly12.taskmanager.core.infrastructure.web.dto.response;

public record ResponseSuccess<T>(boolean success, String message, T data) {
}
