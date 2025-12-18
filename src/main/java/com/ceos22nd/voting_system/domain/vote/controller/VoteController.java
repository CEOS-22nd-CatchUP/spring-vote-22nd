package com.ceos22nd.voting_system.domain.vote.controller;

import com.ceos22nd.voting_system.domain.auth.security.CustomUserDetails;
import com.ceos22nd.voting_system.domain.vote.dto.VoteRequest;
import com.ceos22nd.voting_system.domain.vote.dto.VoteStatusResponse;
import com.ceos22nd.voting_system.domain.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/teams")
    public ResponseEntity<String> voteForTeam(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody VoteRequest request) {

        voteService.voteForTeam(userDetails.getId(), request.getTargetId());

        return ResponseEntity.ok().body("팀 투표가 완료되었습니다.");
    }

    @PostMapping("/parts")
    public ResponseEntity<String> voteForPartLead(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody VoteRequest request) {

        voteService.voteForPartLead(userDetails.getId(), request.getTargetId());

        return ResponseEntity.ok().body("파트장 투표가 완료되었습니다.");
    }

    @GetMapping("/status")
    public ResponseEntity<VoteStatusResponse> getVoteStatus(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long voterId = userDetails.getId();

        boolean hasVotedForTeam = voteService.hasVotedForTeam(voterId);
        boolean hasVotedForPartLead = voteService.hasVotedForPartLead(voterId);

        return ResponseEntity.ok(VoteStatusResponse.of(hasVotedForTeam, hasVotedForPartLead));
    }
}
