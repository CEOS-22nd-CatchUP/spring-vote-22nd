package com.ceos22nd.voting_system.domain.member.service;

import com.ceos22nd.voting_system.domain.member.entity.Member;
import com.ceos22nd.voting_system.domain.member.repository.MemberRepository;
import com.ceos22nd.voting_system.global.dto.ErrorCode;
import com.ceos22nd.voting_system.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
