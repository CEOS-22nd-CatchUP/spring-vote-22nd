package com.ceos22nd.voting_system.domain.member.entity;

import com.ceos22nd.voting_system.domain.member.enums.PartType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", unique = true)
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "part")
    private PartType part;

    @Column(name ="real_name")
    private String realName;

    @Column(name = "is_part_lead_candidate")
    private Boolean isPartLeadCandidate;

    @Column(name = "team_id")
    private String teamId;
}
