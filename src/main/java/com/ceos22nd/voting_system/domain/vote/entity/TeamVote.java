package com.ceos22nd.voting_system.domain.vote.entity;

import com.ceos22nd.voting_system.domain.member.entity.Member;
import com.ceos22nd.voting_system.domain.member.entity.Team;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uk_team_vote_voter", columnNames = "voter_id")
})
public class TeamVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voter_id", nullable = false)
    private Member voter;

    private TeamVote(Member voter, Team team) {
        this.voter = voter;
        this.team = team;
    }

    public static TeamVote createVote(Member voter, Team team) {
        return new TeamVote(voter, team);
    }
}
