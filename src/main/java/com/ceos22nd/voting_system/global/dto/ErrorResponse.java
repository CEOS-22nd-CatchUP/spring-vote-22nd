package com.ceos22nd.voting_system.global.dto;

import lombok.Builder;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        String code,
        String message,
        LocalDateTime timestamp
) {
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }
}
