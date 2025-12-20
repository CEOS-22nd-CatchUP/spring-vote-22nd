package com.ceos22nd.voting_system.domain.vote.repository;

import com.ceos22nd.voting_system.domain.vote.dto.VoteResultResponse;
import com.ceos22nd.voting_system.domain.vote.entity.TeamVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamVoteRepository extends JpaRepository<TeamVote, Long> {

    @Query("SELECT new com.ceos22nd.voting_system.domain.vote.dto.VoteResultResponse(" +
            "    t.id, t.teamName, COUNT(v)) " +
            "FROM TeamVote v " +
            "RIGHT JOIN v.team t " +
            "GROUP BY t.id, t.teamName " +
            "ORDER BY COUNT(v) DESC")
    List<VoteResultResponse> findAllTeamVoteResults();

    Long countByTeamId(Long teamId);

    boolean existsByVoterId(Long voterId);
}
