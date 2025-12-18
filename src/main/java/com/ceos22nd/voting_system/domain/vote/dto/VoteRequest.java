package com.ceos22nd.voting_system.domain.vote.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VoteRequest {
    private Long targetId; // teamId 또는 candidateId

    public VoteRequest(Long targetId) {
        this.targetId = targetId;
    }
}