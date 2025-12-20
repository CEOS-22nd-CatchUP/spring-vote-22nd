package com.ceos22nd.voting_system.domain.vote.controller;

import com.ceos22nd.voting_system.domain.member.entity.Member;
import com.ceos22nd.voting_system.domain.member.entity.Team;
import com.ceos22nd.voting_system.domain.vote.dto.PartLeadCandidateResponse;
import com.ceos22nd.voting_system.domain.vote.dto.TeamCandidateResponse;
import com.ceos22nd.voting_system.domain.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final VoteService voteService;

    @GetMapping("/teams")
    public ResponseEntity<List<TeamCandidateResponse>> getTeamCandidates() {
        List<Team> candidates = voteService.getAllTeams();

        List<TeamCandidateResponse> response = candidates.stream()
                .map(TeamCandidateResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/parts")
    public ResponseEntity<List<PartLeadCandidateResponse>> getPartLeadCandidates() {
        List<Member> candidates = voteService.getCandidates();

        List<PartLeadCandidateResponse> response = candidates.stream()
                .map(PartLeadCandidateResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }
}
