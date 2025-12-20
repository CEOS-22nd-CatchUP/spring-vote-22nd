package com.ceos22nd.voting_system.domain.member.entity;

import com.ceos22nd.voting_system.domain.member.enums.PartType;
import com.ceos22nd.voting_system.global.dto.ErrorCode;
import com.ceos22nd.voting_system.global.exception.BusinessException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", unique = true, nullable = false)
    private String loginId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "part", nullable = false)
    private PartType part;

    @Column(name ="real_name", nullable = false)
    private String realName;

    @Column(name = "is_part_lead_candidate", nullable = false)
    private boolean isPartLeadCandidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(String loginId, String password, String email, PartType part,
                   Team team, String realName, boolean isPartLeadCandidate) {
        this.loginId = loginId;
        this.password = password; // DB 컬럼명과 매핑
        this.email = email;
        this.part = part;
        this.team = team;
        this.realName = realName;
        this.isPartLeadCandidate = isPartLeadCandidate;
    }

    public static Member create(
            String loginId,
            String encryptedPassword,
            String email,
            PartType part,
            Team team,
            String realName,
            boolean isPartLeadCandidate
    ){
        return Member.builder()
                .loginId(loginId)
                .password(encryptedPassword)
                .email(email)
                .part(part)
                .team(team)
                .realName(realName)
                .isPartLeadCandidate(isPartLeadCandidate)
                .build();
    }

    public void validateTeamVote(Long voteTeam) {
        if (this.team.getId().equals(voteTeam)) {
            throw new BusinessException(ErrorCode.SELF_VOTING_NOT_ALLOWED);
        }
    }

    public void validateCandidate() {
        if(!this.isPartLeadCandidate) {
            throw new BusinessException(ErrorCode.CANDIDATE_NOT_FOUND);
        }
    }
}
