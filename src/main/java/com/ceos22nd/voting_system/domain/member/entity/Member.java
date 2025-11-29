package com.ceos22nd.voting_system.domain.member.entity;

import com.ceos22nd.voting_system.domain.member.enums.PartType;
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

    // TODO: Team Entity 와 Join 하기
    @Column(name = "team_id")
    private String teamId;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(String loginId, String password, String email,
                   PartType part, String realName, boolean isPartLeadCandidate) {
        this.loginId = loginId;
        this.password = password; // DB 컬럼명과 매핑
        this.email = email;
        this.part = part;
        this.realName = realName;
        this.isPartLeadCandidate = isPartLeadCandidate;
    }

    public static Member create(
            String loginId,
            String encryptedPassword,
            String email,
            PartType part,
            String realName,
            boolean isPartLeadCandidate
    ){
        return Member.builder()
                .loginId(loginId)
                .password(encryptedPassword)
                .email(email)
                .part(part)
                .realName(realName)
                .isPartLeadCandidate(isPartLeadCandidate)
                .build();
    }
}
