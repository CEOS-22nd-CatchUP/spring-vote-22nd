package com.ceos22nd.voting_system.domain.member.dto;

import com.ceos22nd.voting_system.domain.member.enums.PartType;
import lombok.Builder;

@Builder
public record MemberStatusResponse(
        Long id,
        String name,
        String team,
        PartType part,
        boolean hasVotedForTeam,
        boolean hasVotedForPartLead
) {
    public static MemberStatusResponse of(Long id, String name, String team, PartType part,
                                          boolean teamVote, boolean partVote) {
        return MemberStatusResponse.builder()
                .id(id)
                .name(name)
                .team(team)
                .part(part)
                .hasVotedForTeam(teamVote)
                .hasVotedForPartLead(partVote)
                .build();
    }
}
