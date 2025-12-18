package com.ceos22nd.voting_system.domain.vote.repository;

import com.ceos22nd.voting_system.domain.vote.entity.TeamVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamVoteRepository extends JpaRepository<TeamVote, Long> {

    Long countByTeamId(Long teamId);

    boolean existsByVoterId(Long voterId);
}
