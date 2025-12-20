package com.ceos22nd.voting_system.domain.vote.dto;

import com.ceos22nd.voting_system.domain.member.entity.Team;
import lombok.Builder;

@Builder
public record TeamCandidateResponse(
        Long id,
        String name
) {
    public static TeamCandidateResponse from(Team team) {
        return TeamCandidateResponse.builder()
                .id(team.getId())
                .name(team.getTeamName())
                .build();
    }
}
