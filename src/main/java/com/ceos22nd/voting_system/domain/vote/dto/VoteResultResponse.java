package com.ceos22nd.voting_system.domain.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VoteResultResponse {

    private Long targetId; // teamId 또는 candidateId
    private String targetName; // team name 또는 member name
    private Long voteCount;

    public static VoteResultResponse of(Long targetId, String targetName, Long voteCount) {
        return new VoteResultResponse(targetId, targetName, voteCount);
    }
}