package com.ceos22nd.voting_system.domain.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VoteStatusResponse {
    private boolean teamVote;
    private boolean partLeadVote;

    public static VoteStatusResponse of(boolean teamVote, boolean partLeadVote) {
        return new VoteStatusResponse(teamVote, partLeadVote);
    }
}
