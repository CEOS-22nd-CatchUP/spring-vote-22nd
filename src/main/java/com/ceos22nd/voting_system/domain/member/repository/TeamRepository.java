package com.ceos22nd.voting_system.domain.member.repository;

import com.ceos22nd.voting_system.domain.member.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTeamName(String teamName);
}
