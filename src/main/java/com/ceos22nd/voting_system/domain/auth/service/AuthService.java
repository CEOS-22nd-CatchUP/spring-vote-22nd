package com.ceos22nd.voting_system.domain.auth.service;

import com.ceos22nd.voting_system.domain.auth.dto.LogInRequest;
import com.ceos22nd.voting_system.domain.auth.dto.LogInResponse;
import com.ceos22nd.voting_system.domain.auth.dto.SignUpRequest;
import com.ceos22nd.voting_system.domain.auth.dto.SignUpResponse;
import com.ceos22nd.voting_system.domain.auth.jwt.TokenProvider;
import com.ceos22nd.voting_system.domain.member.entity.Member;
import com.ceos22nd.voting_system.domain.member.entity.Team;
import com.ceos22nd.voting_system.domain.member.repository.MemberRepository;
import com.ceos22nd.voting_system.domain.member.repository.TeamRepository;
import com.ceos22nd.voting_system.global.dto.ErrorCode;
import com.ceos22nd.voting_system.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final TeamRepository teamRepository;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request){
        log.info("회원가입 시도: loginId = {}, email = {}, name = {}",
                request.loginId(), request.email(), request.realName()
        );
        // 아이디 유효성 검사
        validateLoginId(request.loginId());

        // 이메일 유효성 검사
        validateEmail(request.email());

        // 비밀번호 해싱
        String encryptedPassword = passwordEncoder.encode(request.password());

        Team team = teamRepository.findByTeamName(request.team().getInputValue())
                .orElseThrow(() -> new BusinessException(ErrorCode.TEAM_NOT_FOUND));

        // Member 객체 생성
        Member newMember = Member.create(
                request.loginId(),
                encryptedPassword,
                request.email(),
                request.part(),
                team,
                request.realName(),
                request.isPartLeadCandidate()
        );

        // DB 저장
        Member savedMember = memberRepository.save(newMember);

        log.info("회원가입 성공: memberId = {}", savedMember.getId());
        return SignUpResponse.from(savedMember);
    }

    private void validateLoginId(String loginId){
        boolean exist = memberRepository.existsByLoginId(loginId);
        if (exist){
            throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
        }
    }

    private void validateEmail(String email){
        boolean exist = memberRepository.existsByEmail(email);
        if (exist){
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    @Transactional(readOnly = true)
    public LogInResponse logIn(LogInRequest request){
        log.info("로그인 시도: loginId = {}", request.loginId());
        Member member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new BusinessException(ErrorCode.LOGIN_FAILED));

        if (!passwordEncoder.matches(request.password(), member.getPassword())){
            throw new BusinessException(ErrorCode.LOGIN_FAILED);
        }

        String accessToken = tokenProvider.createAccessToken(member.getId());

        log.info("로그인 성공: memberId = {}, loginId = {}", member.getId(), member.getLoginId());
        return new LogInResponse(accessToken);
    }

}
