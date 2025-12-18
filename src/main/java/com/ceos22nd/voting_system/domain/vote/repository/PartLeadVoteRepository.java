package com.ceos22nd.voting_system.domain.vote.repository;

import com.ceos22nd.voting_system.domain.vote.entity.PartLeadVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartLeadVoteRepository extends JpaRepository<PartLeadVote, Long> {

    Long countByCandidateId(Long teamId);

    boolean existsByVoterId(Long voterId);
}
