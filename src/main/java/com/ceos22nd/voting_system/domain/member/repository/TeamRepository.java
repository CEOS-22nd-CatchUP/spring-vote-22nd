package com.ceos22nd.voting_system.domain.member.repository;

import com.ceos22nd.voting_system.domain.member.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
