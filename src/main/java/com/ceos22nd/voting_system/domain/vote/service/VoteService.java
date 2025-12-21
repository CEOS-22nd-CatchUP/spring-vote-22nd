package com.ceos22nd.voting_system.domain.vote.service;

import com.ceos22nd.voting_system.domain.member.entity.Member;
import com.ceos22nd.voting_system.domain.member.entity.Team;
import com.ceos22nd.voting_system.domain.member.enums.PartType;
import com.ceos22nd.voting_system.domain.member.repository.MemberRepository;
import com.ceos22nd.voting_system.domain.member.repository.TeamRepository;
import com.ceos22nd.voting_system.domain.vote.dto.VoteResultResponse;
import com.ceos22nd.voting_system.domain.vote.entity.PartLeadVote;
import com.ceos22nd.voting_system.domain.vote.entity.TeamVote;
import com.ceos22nd.voting_system.domain.vote.repository.PartLeadVoteRepository;
import com.ceos22nd.voting_system.domain.vote.repository.TeamVoteRepository;
import com.ceos22nd.voting_system.global.dto.ErrorCode;
import com.ceos22nd.voting_system.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {

    private final TeamVoteRepository teamVoteRepository;
    private final PartLeadVoteRepository partLeadRepository;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final PartLeadVoteRepository partLeadVoteRepository;

    /**
     * 팀에 투표하기
     */
    @Transactional
    public void voteForTeam(Long voterId, Long teamId) {
        Member voter = getMemberOrThrow(voterId);
        Team team = getTeamOrThrow(teamId);

        validateNotVotedForTeam(voter.getId());
        voter.validateTeamVote(teamId);

        TeamVote teamVote = TeamVote.createVote(voter, team);
        teamVoteRepository.save(teamVote);
    }

    /**
     * 파트장 후보에게 투표하기
     */
    @Transactional
    public void voteForPartLead(Long voterId, Long candidateId) {
        Member voter = getMemberOrThrow(voterId);
        Member candidate = getMemberOrThrow(candidateId);

        validateNotVotedForPartLead(candidate.getId());
        candidate.validateCandidate();

        PartLeadVote partLeadVote = PartLeadVote.createVote(voter, candidate);
        partLeadRepository.save(partLeadVote);
    }

    @Transactional(readOnly = true)
    public List<VoteResultResponse> getTeamVoteResult(){
        try {
            return teamVoteRepository.findAllTeamVoteResults();
        } catch (DataAccessException e){
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
    }

    @Transactional(readOnly = true)
    public List<VoteResultResponse> getPartVoteResult(PartType partType){
        try {
            return partLeadVoteRepository.findAllPartLeadVoteResultsByPart(partType);
        } catch (DataAccessException e){
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
    }

    /**
     * 팀별 투표 결과 조회
     */
    public long getTeamVoteCount(Long teamId) {
        return teamVoteRepository.countByTeamId(teamId);
    }

    /**
     * 후보자별 투표 결과 조회
     */
    public long getPartLeadVoteCount(Long candidateId) {
        return partLeadRepository.countByCandidateId(candidateId);
    }

    /**
     * 후보자 목록 조회
     */
    public List<Member> getCandidates() {
        return memberRepository.findByIsPartLeadCandidateIsTrue();
    }

    /**
     * 모든 팀 조회
     */
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    /**
     * 특정 회원의 팀 투표 여부 확인
     */
    public boolean hasVotedForTeam(Long voterId) {
        return teamVoteRepository.existsByVoterId(voterId);
    }

    /**
     * 특정 회원의 파트장 투표 여부 확인
     */
    public boolean hasVotedForPartLead(Long voterId) {
        return partLeadRepository.existsByVoterId(voterId);
    }

    // =================================================================================================================
    private Member getMemberOrThrow(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private Team getTeamOrThrow(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new BusinessException(ErrorCode.TEAM_NOT_FOUND));
    }

    private void validateNotVotedForTeam(Long voterId) {
        if(teamVoteRepository.existsByVoterId(voterId)) {
            throw new BusinessException(ErrorCode.ALREADY_VOTED);
        }
    }

    private void validateNotVotedForPartLead(Long voterId) {
        if(partLeadRepository.existsByVoterId(voterId)) {
            throw new BusinessException(ErrorCode.ALREADY_VOTED);
        }
    }


}