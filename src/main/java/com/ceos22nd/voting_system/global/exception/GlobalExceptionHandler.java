package com.ceos22nd.voting_system.global.exception;

import com.ceos22nd.voting_system.global.dto.ErrorCode;
import com.ceos22nd.voting_system.global.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.warn("Business Exception: {}", e.getMessage());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {

        String errorMessage = Optional.ofNullable(e.getBindingResult().getFieldError())
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("입력값이 올바르지 않습니다.");

        ErrorResponse response = ErrorResponse.builder()
                        .code("INVALID_INPUT")
                        .message(errorMessage)
                        .timestamp(LocalDateTime.now())
                        .build();

        log.warn("Validation Exception: {}", errorMessage);

        return ResponseEntity
                .status(ErrorCode.INVALID_INPUT_VALUE.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Unhandled Exception: ", e);
        return ErrorResponse.toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
