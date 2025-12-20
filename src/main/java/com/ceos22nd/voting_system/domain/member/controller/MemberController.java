package com.ceos22nd.voting_system.domain.member.controller;

import com.ceos22nd.voting_system.domain.auth.security.CustomUserDetails;
import com.ceos22nd.voting_system.domain.member.dto.MemberStatusResponse;
import com.ceos22nd.voting_system.domain.member.entity.Member;
import com.ceos22nd.voting_system.domain.member.service.MemberService;
import com.ceos22nd.voting_system.domain.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {
    private final MemberService memberService;
    private final VoteService voteService;

    @GetMapping("/me")
    public ResponseEntity<MemberStatusResponse> getMemberStatus(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long memberId = userDetails.getId();

        Member member = memberService.findByMemberId(memberId);

        boolean hasVotedForTeam = voteService.hasVotedForTeam(memberId);
        boolean hasVotedForPart = voteService.hasVotedForPartLead(memberId);

        return ResponseEntity.ok(
                MemberStatusResponse.of(
                        member.getId(),
                        member.getRealName(),
                        hasVotedForTeam,
                        hasVotedForPart
                )
        );
    }
}
