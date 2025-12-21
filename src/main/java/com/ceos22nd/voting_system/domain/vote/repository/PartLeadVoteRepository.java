package com.ceos22nd.voting_system.domain.vote.repository;

import com.ceos22nd.voting_system.domain.member.enums.PartType;
import com.ceos22nd.voting_system.domain.vote.dto.VoteResultResponse;
import com.ceos22nd.voting_system.domain.vote.entity.PartLeadVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartLeadVoteRepository extends JpaRepository<PartLeadVote, Long> {

    @Query("SELECT new com.ceos22nd.voting_system.domain.vote.dto.VoteResultResponse(" +
            "    m.id, m.realName, COUNT(v)) " +
            "FROM PartLeadVote v " +
            "RIGHT JOIN v.candidate m " +
            "WHERE m.isPartLeadCandidate = true AND m.part = :part " +
            "GROUP BY m.id, m.realName " +
            "ORDER BY COUNT(v) DESC")
    List<VoteResultResponse> findAllPartLeadVoteResultsByPart(@Param("part")PartType part);

    Long countByCandidateId(Long teamId);

    boolean existsByVoterId(Long voterId);
}
