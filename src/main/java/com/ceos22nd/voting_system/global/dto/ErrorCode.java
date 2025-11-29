package com.ceos22nd.voting_system.global.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /**
     * 회원가입
     */
    DUPLICATE_LOGIN_ID(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),

    /**
     * 로그인
     */
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "아이디 또는 비빌번호가 일치하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),


    /**
     * Common
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생헀습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
