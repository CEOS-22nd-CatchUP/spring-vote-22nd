package com.ceos22nd.voting_system.domain.member.dto;

import lombok.Builder;

@Builder
public record MemberStatusResponse(
        Long id,
        String name,
        boolean hasVotedForTeam,
        boolean hasVotedForPartLead
) {
    public static MemberStatusResponse of(Long id, String name, boolean teamVote, boolean partVote) {
        return MemberStatusResponse.builder()
                .id(id)
                .name(name)
                .hasVotedForTeam(teamVote)
                .hasVotedForPartLead(partVote)
                .build();
    }
}
