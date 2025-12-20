package com.ceos22nd.voting_system.domain.vote.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TotalVoteResultResponse(
        List<VoteResultResponse> teamVoteResults,
        List<VoteResultResponse> partLeadVoteResults
) {
    public static TotalVoteResultResponse of(
            List<VoteResultResponse> teamVoteResults,
            List<VoteResultResponse> partLeadResults
    ){
        return TotalVoteResultResponse.builder()
                .teamVoteResults(teamVoteResults)
                .partLeadVoteResults(partLeadResults)
                .build();
    }
}
