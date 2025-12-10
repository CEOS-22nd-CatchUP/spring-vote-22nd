package com.ceos22nd.voting_system.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_member")
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
