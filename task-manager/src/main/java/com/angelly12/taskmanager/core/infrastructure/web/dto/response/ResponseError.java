package com.angelly12.taskmanager.core.infrastructure.web.dto.response;

public record ResponseError(int status, String message, String errorCode) {
}
