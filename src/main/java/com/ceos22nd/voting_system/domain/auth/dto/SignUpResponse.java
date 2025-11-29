package com.ceos22nd.voting_system.domain.auth.dto;

import com.ceos22nd.voting_system.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record SignUpResponse(
        String email,
        String partType,
        boolean isPartLeadCandidate
) {
    public static SignUpResponse from(Member member){
        return SignUpResponse.builder()
                .email(member.getEmail())
                .partType(member.getPart().name().toLowerCase())
                .isPartLeadCandidate(member.isPartLeadCandidate())
                .build();
    }
}
