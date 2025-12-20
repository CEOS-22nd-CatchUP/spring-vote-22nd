package com.ceos22nd.voting_system.domain.member.dto;

import lombok.Builder;

@Builder
public record MemberStatusResponse(
        Long id,
        String name,
        String team,
        boolean hasVotedForTeam,
        boolean hasVotedForPartLead
) {
    public static MemberStatusResponse of(Long id, String name, String team, boolean teamVote, boolean partVote) {
        return MemberStatusResponse.builder()
                .id(id)
                .name(name)
                .team(team)
                .hasVotedForTeam(teamVote)
                .hasVotedForPartLead(partVote)
                .build();
    }
}
