package com.ceos22nd.voting_system.domain.vote.dto;

import com.ceos22nd.voting_system.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record PartLeadCandidateResponse(
        Long id,
        String name,
        String part
) {
    public static PartLeadCandidateResponse from(Member member) {
        return PartLeadCandidateResponse.builder()
                .id(member.getId())
                .name(member.getRealName())
                .part(member.getPart().name())
                .build();
    }
}
