package com.ceos22nd.voting_system.domain.vote.entity;

import com.ceos22nd.voting_system.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uk_part_lead_vote_voter", columnNames = "voter_id")})
public class PartLeadVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "candidate_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member candidate;

    @JoinColumn(name = "voter_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member voter;

    private PartLeadVote(Member candidate, Member voter) {
        this.candidate = candidate;
        this.voter = voter;
    }

    public static PartLeadVote createVote(Member candidate, Member voter) {
        return new PartLeadVote(candidate, voter);
    }
}
