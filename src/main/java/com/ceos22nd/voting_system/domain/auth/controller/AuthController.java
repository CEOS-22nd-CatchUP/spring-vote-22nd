package com.ceos22nd.voting_system.domain.auth.controller;

import com.ceos22nd.voting_system.domain.auth.dto.LogInRequest;
import com.ceos22nd.voting_system.domain.auth.dto.LogInResponse;
import com.ceos22nd.voting_system.domain.auth.dto.SignUpRequest;
import com.ceos22nd.voting_system.domain.auth.dto.SignUpResponse;
import com.ceos22nd.voting_system.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 회원가입 API
     *
     * @param request SignUpRequest (회원가입 요청)
     * @return SignUpResponse (회원가입 응답)
     */
    @Valid
    @PostMapping("/api/auth/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request){
        SignUpResponse response = authService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 로그인 API
     *
     * @param request LogInRequest (로그인 요청)
     * @return JwtTokenResponse (JWT 토큰)
     */
    @Valid
    @PostMapping("/api/auth/login")
    public ResponseEntity<LogInResponse> logIn(@RequestBody LogInRequest request){
        LogInResponse response = authService.logIn(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
